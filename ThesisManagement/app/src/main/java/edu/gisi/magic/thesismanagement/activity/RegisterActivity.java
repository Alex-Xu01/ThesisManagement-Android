package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
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
import edu.gisi.magic.thesismanagement.tools.NetTool;
import edu.gisi.magic.thesismanagement.tools.UrlTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity {

	private Button registerButton;
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText passwordEditText2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		this.registerButton = (Button) this.findViewById(R.id.registerButton);
		this.usernameEditText = (EditText) this.findViewById(R.id.usernameEditText);
		this.passwordEditText = (EditText) this.findViewById(R.id.passwordEditText);
		this.passwordEditText2 = (EditText) this.findViewById(R.id.passwordEditText2);

		this.registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Map<String, String> data = new HashMap<String, String>();
				data.put("username", usernameEditText.getText().toString());
				data.put("password", passwordEditText.getText().toString());
				if (!passwordEditText.getText().toString().equals(passwordEditText2.getText().toString()))
					// 两次输入密码不一致
					Toast.makeText(RegisterActivity.this, "两次输入的密码不匹配，请检查", Toast.LENGTH_LONG).show();
				else {
					// 两次输入的密码一致，可以继续注册
					NetTool.post(UrlTool.URL_USER_REGISTER, new Listener<String>() {
						@Override
						public void onResponse(String arg0) {
							try {
								JSONObject obj = new JSONObject(arg0);
								if (obj.getBoolean("result")) {
									Toast.makeText(getApplicationContext(), "注册成功！请登录", Toast.LENGTH_LONG).show();
									finish();
								} else
									Toast.makeText(getApplicationContext(), "注册失败，服务器内部异常！", Toast.LENGTH_LONG).show();
							} catch (JSONException e) {
								e.printStackTrace();
								Toast.makeText(getApplicationContext(), "注册失败，发生异常！", Toast.LENGTH_LONG).show();
							}
						}
					}, new ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError arg0) {
							Toast.makeText(getApplicationContext(), "注册失败，连接服务器错误！", Toast.LENGTH_LONG).show();
						}
					}, data);
				}
			}
		});
	}

}
