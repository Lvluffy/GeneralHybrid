package com.luffy.hybrid.view.titleBar;

/**
 * Created by lvlufei on 2020-04-14
 *
 * @name 公共UI初始化-回调监听
 * @desc
 */
public interface IHybridUIInit {
    /**
     * 设置布局
     *
     * @return
     */
    int setLayoutView();

    /**
     * 初始化接收到的数据
     */
    void initReceiveData();

    /**
     * 初始化布局
     */
    void initView();
}
