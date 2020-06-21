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
public class TelUrlIntercept implements HybridUrlIntercept {

    @Override
    public boolean urlIntercept(Activity mContext, String url) {
        if (!TextUtils.isEmpty(url) && url.startsWith("tel:")) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        }
        return false;
    }
}
