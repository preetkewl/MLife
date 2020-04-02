package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mlife.R;
import com.mlife.utils.DialogProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_OurProjects_WebView extends AppCompatActivity {

    String url;
    Intent intent;
    DialogProgressBar progressBar;

    @BindView(R.id.wv_MldlProjects)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__our_projects__web_view);
        ButterKnife.bind(this);

        try {
            intent = getIntent();
            url = intent.getStringExtra("ProjectLink");
            webView.clearCache(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setWebViewClient(new Activity_OurProjects_WebView.HelloWebViewClient(progressBar));
            webView.loadUrl(url);
        } catch (Exception e) {

        }
    }

    private class HelloWebViewClient extends WebViewClient {

        public HelloWebViewClient(DialogProgressBar progressBar) {
            progressBar.showProgressBar(Activity_OurProjects_WebView.this);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.hideProgressBar();
        }
    }

}