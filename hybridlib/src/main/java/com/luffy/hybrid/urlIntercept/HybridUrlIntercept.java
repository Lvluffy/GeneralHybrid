package com.luffy.hybrid.urlIntercept;

import android.app.Activity;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截
 * @desc
 */
public interface HybridUrlIntercept {
    /**
     * url加载拦截
     *
     * @param mContext
     * @param url
     * @return
     */
    boolean urlIntercept(Activity mContext, String url);
}
