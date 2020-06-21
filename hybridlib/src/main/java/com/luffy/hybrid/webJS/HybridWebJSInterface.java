package com.luffy.hybrid.webJS;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by lvlufei on 2018/3/7
 *
 * @desc 公用的Webview-JS交互接口
 */
public abstract class HybridWebJSInterface {

    public Context mContext;

    public HybridWebJSInterface(Context context) {
        mContext = context;
    }

    /**
     * 处理消息（JS调用原生的方法）
     *
     * @param data 数据（JSON格式）
     */
    @JavascriptInterface
    public abstract void postMessage(String data);
}
