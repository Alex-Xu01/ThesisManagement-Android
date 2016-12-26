package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.config.Urls;
import edu.gisi.magic.thesismanagement.entity.AccountInfo;
import edu.gisi.magic.thesismanagement.volley.CacheTool;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录页
 */
public class LoginActivity extends Activity {

    private Button loginButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private String name;
    private String pwd;
    private long startTime = 0;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CacheTool.setContext(getApplicationContext());
        initView();
    }

    private void initView() {
        loginButton = (Button) findViewById(R.id.loginButton);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        usernameEditText.setHint("请输入登录账号");
        passwordEditText.setHint("请输入登录密码");
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNull()) {
                    doLogin();
                }
            }
        });
    }

    private boolean checkNull() {
        name = usernameEditText.getText().toString().trim();
        pwd = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            usernameEditText.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入登录密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin() {
        final Map<String, String> map = new HashMap<>();
        map.put("loginName", usernameEditText.getText().toString());
        map.put("loginPwd", passwordEditText.getText().toString());
        map.put("android", "1");
        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_USER_LOGIN, AccountInfo.class,
                new Response.Listener<AccountInfo>() {
                    @Override
                    public void onResponse(AccountInfo accountInfo) {
                        if (accountInfo.isResult()) {
                            Toast.makeText(getApplicationContext(), "登录成功，欢迎您回来！", Toast.LENGTH_LONG).show();
                            CacheTool.put("loginName", name);
                            CacheTool.put("loginPwd", pwd);
                            CacheTool.put("accountId", String.valueOf(accountInfo.getAccountId()));
                            CacheTool.put("studentId",String.valueOf(accountInfo.getStudentId()));
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "登录失败，用户名或密码错误", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "登录失败，服务器链接超时", Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        long endTime = System.currentTimeMillis();
        if (endTime - startTime < 2000) {
            finish();
            System.exit(0);
        } else {
            Toast.makeText(this, "确定不登录直接退出?", Toast.LENGTH_SHORT).show();
            startTime = endTime;
        }
    }
}
