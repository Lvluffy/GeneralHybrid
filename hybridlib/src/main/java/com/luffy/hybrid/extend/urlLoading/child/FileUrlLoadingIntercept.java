package com.luffy.hybrid.extend.urlLoading.child;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.luffy.hybrid.extend.urlLoading.base.BaseUrlLoadingIntercept;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-文件
 * @desc
 */
public class FileUrlLoadingIntercept extends BaseUrlLoadingIntercept {

    @Override
    public boolean urlLoadingIntercept(Activity mContext, String url) {
        if (!TextUtils.isEmpty(url) && (url.endsWith(".docx")
                || url.endsWith(".doc")
                || url.endsWith(".xls")
                || url.endsWith(".xlsx")
                || url.endsWith(".ppt")
                || url.endsWith(".pptx")
                || url.endsWith(".pdf")
                || url.endsWith(".apk"))) {
            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }

}
