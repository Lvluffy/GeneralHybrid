package com.luffy.hybrid.extend.urlLoading.base;

import android.app.Activity;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截
 * @desc
 */
public abstract class BaseUrlLoadingIntercept {

    protected String TAG = getClass().getSimpleName();

    /**
     * url加载拦截
     *
     * @param mContext
     * @param url
     * @return
     */
    public abstract boolean urlLoadingIntercept(Activity mContext, String url);
}
