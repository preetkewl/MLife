package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mlife.R;
import com.mlife.utils.Constants;
import com.mlife.utils.DialogProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityWebView extends AppCompatActivity {

    Intent intent;

    @BindView(R.id.WebView)
    WebView webView;

    DialogProgressBar progressBar;

    private class HelloWebViewClient extends WebViewClient {

        public HelloWebViewClient(DialogProgressBar progressBar) {
            progressBar.showProgressBar(ActivityWebView.this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__web_view);
        ButterKnife.bind(this);
        progressBar = new DialogProgressBar();
        intent = getIntent();

        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new HelloWebViewClient(progressBar));

        switch (intent.getStringExtra("Title")) {

            case "About Mahindra Lifespaces":
                webView.loadUrl(Constants.baseUrl + "menu-pages/about.html");
                break;
            case "Sustainable Urbanisation":
                webView.loadUrl(Constants.baseUrl + "menu-pages/sustainable.html");
                break;
            case "Help":
                webView.loadUrl(Constants.baseUrl + "menu-pages/faq.html");
                break;
            case "Contact Us":
                webView.loadUrl(Constants.baseUrl + "menu-pages/contact.html");
                break;
            case "Privacy Policy":
                webView.loadUrl(Constants.baseUrl + "menu-pages/privacy.html");
                break;
            case "Terms & Conditions":
                webView.loadUrl(Constants.baseUrl + "menu-pages/terms.html");
                break;
            case "Groups Terms":
                webView.loadUrl(Constants.baseUrl + "menu-pages/groups-terms.html");
                break;
            case "Classified Terms":
                webView.loadUrl(Constants.baseUrl + "menu-pages/classified-terms.html");
                break;
            default:
                webView.loadUrl(Constants.baseUrl + "menu-pages/groups-terms.html");
                break;


        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

//    @OnClick(R.id.iv_Notification)
//    public void notificationClick() {
////        startActivity(new Intent(getApplicationContext(), Activity_Notification.class));
//    }

//    @OnClick(R.id.iv_MahindraLogo)
//    public void logoClick() {
////        startActivity(new Intent(getApplicationContext(), Activity_Navigation_Dashboard.class));
//    }

}
