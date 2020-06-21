package com.luffy.generalhybrid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luffy.hybrid.ui.HybridActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_hybrid)
    public void onViewClicked() {
        Intent intent = new Intent(this, HybridActivity.class);
        intent.putExtra("title", "测试");
        intent.putExtra("url", "https://www.baidu.com");
        startActivity(intent);
    }
}