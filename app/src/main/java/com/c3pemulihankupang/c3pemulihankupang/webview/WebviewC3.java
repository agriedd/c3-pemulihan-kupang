package com.c3pemulihankupang.c3pemulihankupang.webview;

import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.c3pemulihankupang.c3pemulihankupang.ui.timkami.TimKamiFragment;

public class WebviewC3 extends WebViewClient {

    private TimKamiFragment.LoadingListener listener;

    public WebviewC3(TimKamiFragment.LoadingListener loadingListener) {
        listener = loadingListener;
        listener.onStart();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        listener.onStart();
        view.loadUrl("javascript:(document.onload = function(){" +
                "document.querySelector('.navbar').parentNode.removeChild(document.querySelector('.navbar'))" +
//                    "(elem2 = document.getElementById('id2')); elem2.parentNode.removeChild(elem2); " +
                "})()");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        listener.onComplete();
        view.loadUrl("javascript:(document.onload = function(){" +
                "document.querySelector('.navbar').parentNode.removeChild(document.querySelector('.navbar'))" +
//                    "(elem2 = document.getElementById('id2')); elem2.parentNode.removeChild(elem2); " +
                "})()");
    }

    //    @Override
//    public void onProgressChanged(WebView view, int newProgress) {
//        super.onProgressChanged(view, newProgress);
//        if(newProgress == 100){
//        } else {
//        }
//    }
}
