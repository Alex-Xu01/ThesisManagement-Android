package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.config.Urls;
import edu.gisi.magic.thesismanagement.entity.UserInfo;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;

/**
 * Created by xulih on 2016/12/19.
 */

public class MyAccountActivity extends Activity {

    private TextView title;
    private ImageView imageHead;
    private EditText UserName;
    private Spinner UserSex;
    private EditText UserPhone;
    private EditText UserMail;
    private Button subButton;

    private String imgUrl;
    private String name;
    private String sex;
    private int mSex;
    private String phone;
    private String email;

    private List<String> data_list;
    private ArrayAdapter<String> adapter;

    public static final String TAG = "MyAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        initView();
        getIntents();
        setData();
    }

    private void getIntents() {
        Intent intent = this.getIntent();
        if (intent != null) {
            imgUrl = intent.getStringExtra("imgUrl");
            name = intent.getStringExtra("name");
            sex = intent.getStringExtra("sex");
            if (sex.equals("m"))
                mSex = 0;
            else mSex = 1;
            phone = intent.getStringExtra("phone");
            email = intent.getStringExtra("email");
        }
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        title.setText("个人信息");
        imageHead = (ImageView) findViewById(R.id.imageHead);
        UserName = (EditText) findViewById(R.id.UserName);

        UserSex = (Spinner) findViewById(R.id.UserSex);
        data_list = new ArrayList<>();
        data_list.add("男");
        data_list.add("女");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserSex.setAdapter(adapter);

        UserPhone = (EditText) findViewById(R.id.UserPhone);
        UserMail = (EditText) findViewById(R.id.UserMail);
        subButton = (Button) findViewById(R.id.subButton);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UserName.getText().toString().equals(""))
                    name = UserName.getHint().toString();
                else
                    name = UserName.getText().toString();

                sex = UserSex.getSelectedItem().toString();

                if (UserPhone.getText().toString().equals(""))
                    phone = UserPhone.getHint().toString();
                else
                    phone = UserPhone.getText().toString();

                if (UserMail.getText().toString().equals(""))
                    email = UserMail.getHint().toString();
                else
                    email = UserMail.getText().toString();

                final Map<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("sex", sex);
                map.put("phone", phone);
                map.put("email", email);
                VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_USER_LOGIN, UserInfo.class,
                        new Response.Listener<UserInfo>() {
                            @Override
                            public void onResponse(UserInfo userInfo) {
                                if (userInfo.isResult())
                                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(), "修改失败,所填信息错误", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "修改失败，无法连接到服务器", Toast.LENGTH_LONG).show();
                                Log.e(TAG, error.getMessage(), error);
                            }
                        });
            }
        });
    }

    private void setData() {

        getViewHead(imgUrl);
        UserName.setHint(name);
        UserSex.setSelection(mSex);
        UserPhone.setHint(phone);
        UserMail.setHint(email);
    }

    private void getViewHead(String url) {
        VolleyManager.newInstance().ImageRequest(TAG, url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageHead.setImageBitmap(bitmap);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });

    }

    public static void startAc(Context context, String imgUrl, String name, String sex, String phone, String email) {
        Intent intent = new Intent(context, MyAccountActivity.class);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("name", name);
        intent.putExtra("sex", sex);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        context.startActivity(intent);
    }

    public void back(View view) {
        finish();
    }

}
