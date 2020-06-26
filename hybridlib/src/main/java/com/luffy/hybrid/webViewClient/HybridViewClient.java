package com.luffy.hybrid.webViewClient;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name 公用的web客户端
 * @desc
 */
public class HybridViewClient extends WebViewClient {

    private IHybridViewClient mIHybridViewClient;

    public HybridViewClient(IHybridViewClient mIHybridViewClient) {
        this.mIHybridViewClient = mIHybridViewClient;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (mIHybridViewClient != null && mIHybridViewClient.shouldOverrideUrlLoadingBase(view, url)) {
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (mIHybridViewClient != null && mIHybridViewClient.shouldOverrideUrlLoadingBase(view, request)) {
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mIHybridViewClient != null) {
            mIHybridViewClient.onPageStartedBase(view, url, favicon);
        }
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mIHybridViewClient != null) {
            mIHybridViewClient.onPageFinishedBase(view, url);
        }
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        if (mIHybridViewClient != null) {
            mIHybridViewClient.onReceivedSslErrorBase(view, handler, error);
        }
        super.onReceivedSslError(view, handler, error);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (mIHybridViewClient != null) {
            mIHybridViewClient.onReceivedErrorBase(view, request, error);
        }
        super.onReceivedError(view, request, error);
    }

}
