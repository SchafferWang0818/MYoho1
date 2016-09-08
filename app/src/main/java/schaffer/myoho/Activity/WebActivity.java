package schaffer.myoho.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import schaffer.myoho.Base.BaseActivity;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/9/5.
 */
public class WebActivity extends BaseActivity {
    private android.widget.ProgressBar webpb;
    private WebView webweb;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        this.webweb = (WebView) findViewById(R.id.web_web);
        this.webpb = (ProgressBar) findViewById(R.id.web_pb);
        webpb.setBackgroundColor(Color.WHITE);
//        webweb.
        webpb.setDrawingCacheBackgroundColor(Color.parseColor("#ff4081"));
        webweb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 1) {
                    webpb.setVisibility(View.VISIBLE);
                }
                webpb.setProgress(newProgress);
                if (newProgress == 100) {
                    webpb.setVisibility(View.INVISIBLE);
                }
            }

        });
        webweb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webweb.loadUrl(WebActivity.this.url);
                return true;
            }
        });
        webweb.getSettings().setJavaScriptEnabled(true);
    }


    public void finish(View view) {
        finish();
    }


    @Override
    public void finish() {
        if (webweb.canGoBack()) {
            webweb.goBack();
        } else {
            super.finish();
        }
    }
}
