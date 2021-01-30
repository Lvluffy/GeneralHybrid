package com.luffy.hybrid.view.titleBar;

import android.view.View;

/**
 * Created by lvlufei on 2020-04-14
 *
 * @name 公共标题栏布局回调监听（Activity、fragment）
 * @desc
 */
public interface IHybridTitleLayout {

    /**
     * 设置标题栏可见与否
     *
     * @return
     */
    boolean visibilityTitleLayout();

    /**
     * 设置返回可见与否
     *
     * @return
     */
    boolean visibilityBack();

    /**
     * 设置返回可见与否
     *
     * @return
     */
    boolean visibilityBackImg();

    /**
     * 设置返回可见与否
     *
     * @return
     */
    boolean visibilityBackTxt();

    /**
     * 设置关闭可见与否
     *
     * @return
     */
    boolean visibilityCloseImg();

    /**
     * 设置关闭可见与否
     *
     * @param b
     */
    void visibilityCloseImg(boolean b);

    /**
     * 设置更多可见与否
     *
     * @return
     */
    boolean visibilityMore();

    /**
     * 设置更多可见与否
     *
     * @return
     */
    boolean visibilityMoreView();

    /**
     * 设置更多可见与否
     *
     * @return
     */
    boolean visibilityMoreImg();

    /**
     * 设置更多可见与否
     *
     * @return
     */
    boolean visibilityMoreTxt();

    /**
     * 设置分割线可见与否
     *
     * @return
     */
    boolean visibilityDivider();

    /**
     * 设置标题栏背景色
     *
     * @return
     */
    int setBgColor();

    /**
     * 设置不透明度
     *
     * @@return alpha（0-255：0为完全透明，255为不透明）
     */
    int setAlpha();

    /**
     * 设置返回图标
     *
     * @return
     */
    int setBackImg();

    /**
     * 设置关闭图标
     *
     * @return
     */
    int setCloseImg();

    /**
     * 设置返回
     *
     * @return
     */
    int setBackTextInt();

    /**
     * 设置返回
     *
     * @return
     */
    String setBackTextString();

    /**
     * 设置返回文案（网络获取）
     *
     * @param backStr
     */
    void setBackTextString(String backStr);

    /**
     * 设置返回字体颜色
     *
     * @return
     */
    int setBackTextColor();

    /**
     * 设置返回字体大小
     *
     * @return
     */
    int setBackTextSize();

    /**
     * 设置标题
     *
     * @return
     */
    int setTitleInt();

    /**
     * 设置标题
     *
     * @return
     */
    String setTitleString();

    /**
     * 设置标题文案（网络获取）
     *
     * @param titleStr
     */
    void setTitleString(String titleStr);

    /**
     * 设置标题字体颜色
     *
     * @return
     */
    int setTitleColor();

    /**
     * 设置标题字体大小
     *
     * @return
     */
    int setTitleSize();

    /**
     * 设置更多View
     *
     * @return
     */
    View setMoreView();

    /**
     * 设置更多文案
     *
     * @return
     */
    int setMoreTextInt();

    /**
     * 设置更多文案
     *
     * @return
     */
    String setMoreTextString();

    /**
     * 设置更多文案（网络获取）
     *
     * @param moreStr
     */
    void setMoreTextString(String moreStr);

    /**
     * 设置更多字体颜色
     *
     * @return
     */
    int setMoreTextColor();

    /**
     * 设置更多字体大小
     *
     * @return
     */
    int setMoreTextSize();

    /**
     * 设置更多图标
     *
     * @return
     */
    int setMoreImg();

    /**
     * 设置分割线颜色
     *
     * @return
     */
    int setDividerColor();
}
