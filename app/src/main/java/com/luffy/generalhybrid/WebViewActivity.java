package com.luffy.generalhybrid;

import com.luffy.hybrid.ui.HybridActivity;
import com.luffy.hybrid.ui.HybridFragment;

/**
 * Created by lvlufei on 2018/1/11
 *
 * @desc 公用的Webview模板
 */
public class WebViewActivity extends HybridActivity {

    @Override
    public HybridFragment doCreate() {
        return new WebViewFragment();
    }
}
