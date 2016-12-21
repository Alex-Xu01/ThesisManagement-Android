package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import edu.gisi.magic.thesismanagement.R;

/**
 * Created by xulih on 2016/12/19.
 */

public class WebViewActivity extends Activity {

    private TextView title;

    private WebView webView;
    private String url;
    private String viewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        getIntents();
        initView();
    }

    private void getIntents() {
        Intent intent = this.getIntent();
        if (intent != null) {
            viewTitle = intent.getStringExtra("title");
            url = intent.getStringExtra("url");
        }
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        title.setText(viewTitle);
        webView = (WebView) findViewById(R.id.web_view_container);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }


    public static void startAc(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}
