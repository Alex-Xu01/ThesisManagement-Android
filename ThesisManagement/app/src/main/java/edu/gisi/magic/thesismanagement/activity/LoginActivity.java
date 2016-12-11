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
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.config.Urls;
import edu.gisi.magic.thesismanagement.entity.UserInfo;
import edu.gisi.magic.thesismanagement.volley.CacheTool;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录页
 */
public class LoginActivity extends Activity {

    private Button loginButton;
    private Button registerButton;
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
        registerButton = (Button) findViewById(R.id.registerButton);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNull()) {
                    doLogin();
                }
            }
        });

        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
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
//        map.put("username", usernameEditText.getText().toString());
//        map.put("password", passwordEditText.getText().toString());
        map.put("username", "admin");
        map.put("password", "111111");
        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_USER_LOGIN, UserInfo.class,
                new Response.Listener<UserInfo>() {
                    @Override
                    public void onResponse(UserInfo userInfo) {
                        if (userInfo.isResult()) {
                            Toast.makeText(getApplicationContext(), "登录成功，欢迎您回来！", Toast.LENGTH_LONG).show();
                            CacheTool.put("username", name);
                            CacheTool.put("userId", String.valueOf(userInfo.getId()));
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
                        Toast.makeText(getApplicationContext(), "登录失败，无法连接到服务器", Toast.LENGTH_LONG).show();
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
