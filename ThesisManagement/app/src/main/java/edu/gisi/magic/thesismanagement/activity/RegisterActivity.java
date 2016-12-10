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
import edu.gisi.magic.thesismanagement.entity.UserInfo;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;


public class RegisterActivity extends Activity {

    private Button registerButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordEditText2;

    public static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button) findViewById(R.id.registerButton);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText2 = (EditText) findViewById(R.id.passwordEditText2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", usernameEditText.getText().toString());
                map.put("password", passwordEditText.getText().toString());
                if (!passwordEditText.getText().toString().equals(passwordEditText2.getText().toString())) {
                    // 两次输入密码不一致
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不匹配，请检查", Toast.LENGTH_LONG).show();
                } else {
                    VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_USER_REGISTER, UserInfo.class,
                            new Response.Listener<UserInfo>() {
                                @Override
                                public void onResponse(UserInfo userInfo) {
                                    if (userInfo.isResult()) {
                                        Toast.makeText(getApplicationContext(), "注册成功！请登录", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else
                                        Toast.makeText(getApplicationContext(), "注册失败，发生异常！", Toast.LENGTH_LONG).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "注册失败，连接服务器错误！", Toast.LENGTH_LONG).show();
                                    Log.e(TAG, error.getMessage(), error);
                                }
                            });
                }
            }
        });
    }

}
