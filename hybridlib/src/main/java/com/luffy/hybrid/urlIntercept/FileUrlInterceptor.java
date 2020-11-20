package com.luffy.hybrid.urlIntercept;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;


/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-文件
 * @desc
 */
public class FileUrlInterceptor implements HybridUrlInterceptor {

    @Override
    public boolean urlIntercept(Activity mContext, String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        if (url.endsWith(".docx")
                || url.endsWith(".doc")
                || url.endsWith(".xls")
                || url.endsWith(".xlsx")
                || url.endsWith(".ppt")
                || url.endsWith(".pptx")
                || url.endsWith(".pdf")
                || url.endsWith(".apk")) {
            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }

}
