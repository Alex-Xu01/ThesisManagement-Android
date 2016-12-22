package edu.gisi.magic.thesismanagement.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.activity.AboutUsActivity;
import edu.gisi.magic.thesismanagement.activity.ChangePasswordActivity;
import edu.gisi.magic.thesismanagement.activity.LoginActivity;
import edu.gisi.magic.thesismanagement.activity.MyAccountActivity;
import edu.gisi.magic.thesismanagement.activity.WebViewActivity;
import edu.gisi.magic.thesismanagement.config.Urls;
import edu.gisi.magic.thesismanagement.entity.UserInfo;
import edu.gisi.magic.thesismanagement.volley.CacheTool;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;

/**
 * 个人中心
 */

public class UserCenterFragment extends Fragment {

    private ImageView imageView;

    private TextView nameTextView;
    private TextView depTextView;
    private TextView AccountMsg;
    private TextView ChangePwd;
    private TextView toKefu;
    private TextView toAboutUs;

    private Button logoutButton;

    private String name;
    private String imgUrl;
    private String sex;
    private String phone;
    private String email;

    private String kefuUrl = "https://www.sobot.com/chat/h5/index.html?sysNum=e4a4159a5ee547b782e102fac25ceddb&source=2";

    public static final String TAG = "UserCenterFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_center, null);
        initView(view);
        setView();
        return view;
    }

    private void initView(View view) {
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        depTextView = (TextView) view.findViewById(R.id.depTextView);
        AccountMsg = (TextView) view.findViewById(R.id.UserMsg);
        AccountMsg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAccountActivity.startAc(getActivity(), imgUrl, name, sex, phone, email);
            }
        });
        ChangePwd= (TextView) view.findViewById(R.id.changePassword);
        ChangePwd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });
        toKefu = (TextView) view.findViewById(R.id.toKefu);
        toKefu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.startAc(getActivity(), "在线客服", kefuUrl);
            }
        });
        toAboutUs = (TextView) view.findViewById(R.id.toAboutUs);
        toAboutUs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
            }
        });
        imageView = (ImageView) view.findViewById(R.id.imageHead);
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void setView() {
        Map<String, String> map = new HashMap<>();
        map.put("username", CacheTool.get("username"));

        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_USER_INFO, UserInfo.class,
                new Response.Listener<UserInfo>() {
                    @Override
                    public void onResponse(UserInfo userInfo) {
                        imgUrl = userInfo.getImgUrl();
                        name = userInfo.getName();
                        sex = userInfo.getSex();
                        phone = userInfo.getPhone();
                        email = userInfo.getEmail();
                        nameTextView.setText(name);
                        depTextView.setText(userInfo.getDep());
                        getViewHead();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
    }

    private void getViewHead() {
        VolleyManager.newInstance().ImageRequest(TAG, imgUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });

    }
}
