package com.luffy.hybrid.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by lvlufei on 2018/1/11
 *
 * @desc 公用的Webview模板
 */
public class HybridActivity extends AppCompatActivity implements IHybridCreate {

    protected HybridFragment mHybridFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHybridFragment = doCreate();
        mHybridFragment.setArguments(getIntent().getExtras());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, mHybridFragment).commitAllowingStateLoss();
    }

    @Override
    public HybridFragment doCreate() {
        return new HybridFragment();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mHybridFragment.webCanGoBack()) {
                mHybridFragment.webview.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
