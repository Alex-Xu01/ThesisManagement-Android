package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import edu.gisi.magic.thesismanagement.R;

import edu.gisi.magic.thesismanagement.tools.CacheTool;
import edu.gisi.magic.thesismanagement.tools.NetTool;
import edu.gisi.magic.thesismanagement.tools.UrlTool;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NetTool.setContext(getApplicationContext());
        CacheTool.setContext(getApplicationContext());

        setContentView(R.layout.activity_login);

        this.loginButton = (Button) this.findViewById(R.id.loginButton);
        this.registerButton = (Button) this.findViewById(R.id.registerButton);
        this.usernameEditText = (EditText) this.findViewById(R.id.usernameEditText);
        this.passwordEditText = (EditText) this.findViewById(R.id.passwordEditText);

        this.loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Map<String, String> data = new HashMap<>();
                data.put("username", "admin");
                data.put("password", "111111");
                JSONObject jsonObject = new JSONObject(data);
//				data.put("username", usernameEditText.getText().toString());
//				data.put("password", passwordEditText.getText().toString());
				NetTool.post(UrlTool.URL_USER_LOGIN, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						try {
							if (new JSONObject(arg0).getBoolean("result")) {
								Toast.makeText(getApplicationContext(), "登录成功，欢迎您回来！", Toast.LENGTH_LONG).show();
								CacheTool.put("username", usernameEditText.getText().toString());
								CacheTool.put("userId", new JSONObject(arg0).getLong("id") + "");
								Intent intent = new Intent();
								intent.setClass(LoginActivity.this, MainActivity.class);
								startActivity(intent);
								finish();
							} else {
								Toast.makeText(getApplicationContext(), "登录失败，用户名或密码错误", Toast.LENGTH_LONG).show();
							}
						} catch (JSONException e) {
							Toast.makeText(getApplicationContext(), "登录失败，服务器返回数据异常", Toast.LENGTH_LONG).show();
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						Toast.makeText(getApplicationContext(), "登录失败，无法连接到服务器", Toast.LENGTH_LONG).show();
					}
				}, data);
            }
        });

        this.registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
