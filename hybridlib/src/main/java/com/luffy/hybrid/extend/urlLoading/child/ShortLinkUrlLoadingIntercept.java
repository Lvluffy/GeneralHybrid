package com.luffy.hybrid.extend.urlLoading.child;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.luffy.hybrid.extend.urlLoading.base.BaseUrlLoadingIntercept;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-短链接
 * @desc
 */
public class ShortLinkUrlLoadingIntercept extends BaseUrlLoadingIntercept {

    @Override
    public boolean urlLoadingIntercept(Activity mContext, String url) {
        if (!TextUtils.isEmpty(url) && !url.startsWith("http://") && !url.startsWith("https://")) {
            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }
}
