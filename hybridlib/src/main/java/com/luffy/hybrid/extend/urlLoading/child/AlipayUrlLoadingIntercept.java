package com.luffy.hybrid.extend.urlLoading.child;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.luffy.hybrid.extend.urlLoading.base.BaseUrlLoadingIntercept;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-支付宝
 * @desc
 */
public class AlipayUrlLoadingIntercept extends BaseUrlLoadingIntercept {

    @Override
    public boolean urlLoadingIntercept(Activity mContext, String url) {
        if (!TextUtils.isEmpty(url) && (url.startsWith("alipays:") || url.startsWith("alipay"))) {
            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception e) {
                Log.e(TAG, "未检测到支付宝客户端，请安装后重试。");
            }
            return true;
        }
        return false;
    }

}
