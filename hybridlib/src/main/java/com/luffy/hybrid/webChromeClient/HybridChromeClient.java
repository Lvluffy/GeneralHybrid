package com.luffy.hybrid.webChromeClient;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.luffy.hybrid.video.VideoImpl;


/**
 * Created by lvlufei on 2019/6/20
 *
 * @name 公用的web浏览器客户端
 * @desc
 */
public class HybridChromeClient extends WebChromeClient {

    private IHybridChromeClient mIHybridChromeClient;
    private VideoImpl mVideoImpl;

    public HybridChromeClient(Activity mContext, WebView mWebView, IHybridChromeClient mIHybridChromeClient) {
        this.mIHybridChromeClient = mIHybridChromeClient;
        mVideoImpl = new VideoImpl(mContext, mWebView);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (mIHybridChromeClient != null) {
            mIHybridChromeClient.onProgressChangedBase(view, newProgress);
        }
    }

    /**
     * 获取WebView标题
     *
     * @param view  　WebView
     * @param title 　WebView标题
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mIHybridChromeClient != null) {
            mIHybridChromeClient.onReceivedTitleBase(view, title);
        }
    }

    /**
     * 网页点击全屏会回调方法
     *
     * @param view
     * @param callback
     */
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        mVideoImpl.onShowCustomView(view, callback);
    }

    /**
     * 关闭全屏回调方法
     */
    @Override
    public void onHideCustomView() {
        mVideoImpl.onHideCustomView();
    }
}
