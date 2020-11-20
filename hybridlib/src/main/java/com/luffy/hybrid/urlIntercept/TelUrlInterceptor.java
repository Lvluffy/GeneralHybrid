package com.luffy.hybrid.urlIntercept;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-打电话
 * @desc
 */
public class TelUrlInterceptor implements HybridUrlInterceptor {

    @Override
    public boolean urlIntercept(Activity mContext, String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        if (url.startsWith("tel:")) {
            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }
}
