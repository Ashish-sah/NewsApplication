package com.ashish.NewsApplication;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class newsExplorer extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_explorer);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.newsExplorer));
        webView = findViewById(R.id.webview);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String newsUrl = intent.getStringExtra("newsUrl");

        webView.loadUrl(newsUrl);
        //this allow  url to load in the app only  not in browser
        webView.setWebViewClient(new WebViewClient());

    }
}