package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.config.Urls;
import edu.gisi.magic.thesismanagement.entity.AccountInfo;
import edu.gisi.magic.thesismanagement.entity.ThesisConmmit;
import edu.gisi.magic.thesismanagement.volley.CacheTool;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;

/**
 * Created by AlexXu on 2016/12/12.
 */

public class ThesisDetailActivity extends Activity {

    private TextView title;

    private TextView mMainTitle;

    private TextView mSubTitle;

    private TextView mType;

    private TextView mOrigin;

    private TextView mTeacher;

    private TextView mNumber;

    private TextView mDepartment;

    private TextView mContent;

    private Button mButton;

    private String id;

    private boolean showBtn;

    public static final String TAG = "ThesisDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesis_detail);
        initView();
        initData();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        title.setText("论文信息");
        mMainTitle = (TextView) findViewById(R.id.tv_mainTitle);
        mSubTitle = (TextView) findViewById(R.id.tv_subTitle);
        mType = (TextView) findViewById(R.id.tv_type);
        mOrigin = (TextView) findViewById(R.id.tv_origin);
        mTeacher = (TextView) findViewById(R.id.tv_teacher);
        mNumber = (TextView) findViewById(R.id.tv_number);
        mDepartment = (TextView) findViewById(R.id.tv_department);
        mContent = (TextView) findViewById(R.id.tv_content);
        mButton = (Button) findViewById(R.id.enterButton);
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        showBtn = intent.getBooleanExtra("showBtn", false);
        if (showBtn) {
            mButton.setVisibility(View.VISIBLE);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Map<String, String> map = new HashMap<>();
                    map.put("id", id);
                    map.put("username", CacheTool.get("username"));
                    VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_THESIS, ThesisConmmit.class,
                            new Response.Listener<ThesisConmmit>() {
                                @Override
                                public void onResponse(ThesisConmmit thesisConmmit) {
                                    //TODO 将选题信息上传
                                    Toast.makeText(getApplicationContext(), id + " & " + CacheTool.get("username"), Toast.LENGTH_LONG).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "网络错误，无法连接到服务器", Toast.LENGTH_LONG).show();
                                    Log.e(TAG, error.getMessage(), error);
                                }
                            });
                }
            });
        }

        final Map<String, String> map = new HashMap<>();
        map.put("id", id);
        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_THESIS, ThesisConmmit.class,
                new Response.Listener<ThesisConmmit>() {
                    @Override
                    public void onResponse(ThesisConmmit thesisConmmit) {
                        mMainTitle.setText(thesisConmmit.getMainTitle());
                        mSubTitle.setText(getString(R.string.font_subtitle, thesisConmmit.getSubTitle()));
                        mType.setText(getString(R.string.font_type, thesisConmmit.getType()));
                        mOrigin.setText(getString(R.string.font_origin, thesisConmmit.getOrigin()));
                        mTeacher.setText(getString(R.string.font_teacher, thesisConmmit.getTeacher()));
                        mNumber.setText(getString(R.string.font_number, thesisConmmit.getNumber()));
                        mDepartment.setText(getString(R.string.font_department, thesisConmmit.getDepartment()));
                        mContent.setText(getString(R.string.font_content, thesisConmmit.getContent()));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "网络错误，无法连接到服务器", Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
    }


    public static void startAc(Context context, String id, boolean showBtn) {
        Intent intent = new Intent(context, ThesisDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("showBtn", showBtn);
        context.startActivity(intent);
    }

    public void back(View view) {
        finish();
    }

}
