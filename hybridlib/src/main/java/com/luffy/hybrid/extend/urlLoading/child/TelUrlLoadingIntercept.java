package com.luffy.hybrid.extend.urlLoading.child;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.luffy.hybrid.extend.urlLoading.base.BaseUrlLoadingIntercept;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-打电话
 * @desc
 */
public class TelUrlLoadingIntercept extends BaseUrlLoadingIntercept {

    @Override
    public boolean urlLoadingIntercept(Activity mContext, String url) {
        if (!TextUtils.isEmpty(url) && url.startsWith("tel:")) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        }
        return false;
    }
}
