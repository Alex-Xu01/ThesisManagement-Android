package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.config.Urls;
import edu.gisi.magic.thesismanagement.entity.AccountInfo;
import edu.gisi.magic.thesismanagement.volley.CacheTool;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;

/**
 * Created by xulih on 2016/12/22.
 */

public class ChangePasswordActivity extends Activity {

    private Button subButton;
    private EditText ordPwd;
    private EditText newPwd;
    private EditText againPwd;

    public static final String TAG = "ChangePasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);
        subButton = (Button) findViewById(R.id.subButton);
        ordPwd = (EditText) findViewById(R.id.ordPwd);
        newPwd = (EditText) findViewById(R.id.newPwd);
        againPwd = (EditText) findViewById(R.id.againPwd);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CacheTool.get("loginPwd").equals(ordPwd.getText().toString())) {

                    Map<String, String> map = new HashMap<>();
                    map.put("id", CacheTool.get("accountId"));
                    map.put("newPwd", againPwd.getText().toString());
                    map.put("android", "1");
                    if (!newPwd.getText().toString().equals(againPwd.getText().toString())) {
                        // 两次输入密码不一致
                        Toast.makeText(ChangePasswordActivity.this, "两次输入的密码不匹配，请检查", Toast.LENGTH_LONG).show();
                    } else {
                        if (CacheTool.get("loginPwd").equals(againPwd.getText().toString())) {
                            newPwd.setText("");
                            againPwd.setText("");
                            Toast.makeText(getApplicationContext(), "新密码不能与原密码相同", Toast.LENGTH_LONG).show();
                        } else {
                            VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_USER_CHANGE_PASSWORD, AccountInfo.class,
                                    new Response.Listener<AccountInfo>() {
                                        @Override
                                        public void onResponse(AccountInfo accountInfo) {
                                            if (accountInfo.isResult()) {
                                                Toast.makeText(getApplicationContext(), "密码修改成功", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else
                                                Toast.makeText(getApplicationContext(), "密码修改失败，发生异常！", Toast.LENGTH_LONG).show();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(getApplicationContext(), "密码修改失败，连接服务器错误！", Toast.LENGTH_LONG).show();
                                            Log.e(TAG, error.getMessage(), error);
                                        }
                                    });
                        }
                    }
                } else {
                    ordPwd.setText("");
                    Toast.makeText(getApplicationContext(), "原密码输入错误，请重新输入", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
