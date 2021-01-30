package com.luffy.hybrid.view.titleBar;

/**
 * Created by lvlufei on 2020-04-14
 *
 * @name 公共标题栏回点击事件调监听（Activity、fragment）
 * @desc
 */
public interface IHybridTitleClick {
    /**
     * 导航栏点击事件
     */
    void onClickNav();

    /**
     * 返回按钮点击事件
     */
    void onClickBack();

    /**
     * 更多按钮点击事件
     */
    void onClickMore();

    /**
     * 关闭按钮点击事件
     */
    void onClickClose();
}
