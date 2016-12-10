package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;

import edu.gisi.magic.thesismanagement.R;

/**
 * 启动页
 */
public class SplashActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);
        switchToOtherActivity();
    }

    private void switchToOtherActivity() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);//2s
                    startAc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    startAc();
                }
            }
        }).start();
    }

    private void startAc() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switchToOtherActivity();
    }

}
