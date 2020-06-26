package com.luffy.hybrid.urlIntercept;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-支付宝
 * @desc
 */
public class AlipayUrlInterceptor implements HybridUrlInterceptor {

    @Override
    public boolean urlIntercept(Activity mContext, String url) {
        if (!TextUtils.isEmpty(url) && (url.startsWith("alipays:") || url.startsWith("alipay"))) {
            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception e) {
                Toast.makeText(mContext, "未检测到支付宝客户端，请安装后重试。", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }

}
