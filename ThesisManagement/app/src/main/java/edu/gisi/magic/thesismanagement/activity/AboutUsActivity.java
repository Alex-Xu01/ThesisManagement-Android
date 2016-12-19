package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gisi.magic.thesismanagement.R;

/**
 * Created by xulih on 2016/12/19.
 */

public class AboutUsActivity extends Activity {

    private TextView title;
    private TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        initView();
    }

    private void initView() {

        title = (TextView) findViewById(R.id.title);
        title.setText("关于我们");

        tv_version = (TextView) findViewById(R.id.tv_version);
        tv_version.setText(getString(R.string.version, "   V"+getVersion(AboutUsActivity.this)));

    }

    private String getVersion(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public void back(View view) {
        finish();
    }
}
