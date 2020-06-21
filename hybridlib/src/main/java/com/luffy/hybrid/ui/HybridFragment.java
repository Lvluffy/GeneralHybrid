package com.luffy.hybrid.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.luffy.hybrid.R;
import com.luffy.hybrid.urlIntercept.AlipayUrlIntercept;
import com.luffy.hybrid.urlIntercept.FileUrlIntercept;
import com.luffy.hybrid.urlIntercept.ShortLinkUrlIntercept;
import com.luffy.hybrid.urlIntercept.TelUrlIntercept;
import com.luffy.hybrid.webChromeClient.HybridChromeClient;
import com.luffy.hybrid.webChromeClient.IHybridChromeClient;
import com.luffy.hybrid.webViewClient.HybridViewClient;
import com.luffy.hybrid.webViewClient.IHybridViewClient;
import com.luffy.lifycycle.titlebarlib.TitleBarWidget;
import com.luffy.lifycycle.titlebarlib.impl.ITitleClick;
import com.luffy.lifycycle.titlebarlib.impl.ITitleLayout;
import com.luffy.lifycycle.titlebarlib.impl.IUIInit;
import com.luffy.view.generalemptylib.GeneralEmpty;

/**
 * Created by lvlufei on 2018/5/21
 *
 * @desc 公用的Webview模板
 */
public class HybridFragment extends Fragment implements IUIInit<Fragment>,
        ITitleClick,
        ITitleLayout,
        IHybrid,
        IHybridViewClient,
        IHybridChromeClient {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_URL = "url";

    //上下文对象
    public Activity mContext;
    //界面是否已经附属，默认：false
    protected boolean isAttach;
    //视图是否已经初始化，默认：false
    protected boolean isInit;

    protected View rootView;
    protected WebView webview;
    protected String requestUrl;
    protected boolean isError;
    protected LinearLayout webParentView;
    protected View errorView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttach = true;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*初始化界面*/
        if (rootView == null) {
            mContext = getActivity();
            /*绑定布局*/
            rootView = inflater.inflate(R.layout.root_layout, null);
            TitleBarWidget.getInstance().setRootView(rootView);
            /*初始化标题栏控件*/
            TitleBarWidget.getInstance().initTitlebarWidget();
            /*初始化标题栏事件*/
            TitleBarWidget.getInstance().initTitleEvent(this, inflater);
            /*初始化标题栏配置*/
            TitleBarWidget.getInstance().initTitlebarConfig(this, getActivity());
            /*绑定控件*/
            this.bindButterKnife(this);
            /*初始化接收到的数据*/
            this.initReceiveData();
            /*界面对用户可见时，初始化界面和数据*/
            if (getUserVisibleHint()) {
                /*初始化界面*/
                this.initView();
                /*更改初始化状态*/
                isInit = true;
            }
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isAttach && !isInit && getUserVisibleHint()) {
            /*初始化界面*/
            initView();
            /*更改初始化状态*/
            isInit = true;
            return;
        }
        if (isAttach && isInit && getUserVisibleHint()) {
            /*初始化界面*/
            initView();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isAttach = false;
    }

    @Override
    public int setLayoutView() {
        return R.layout.hybrid_layout;
    }

    @Override
    public void bindButterKnife(Fragment target) {

    }

    @Override
    public void initReceiveData() {
        if (getArguments().containsKey(EXTRA_TITLE)) {
            setTitleString(getArguments().getString(EXTRA_TITLE));
        }
        if (getArguments().containsKey(EXTRA_URL)) {
            requestUrl = getArguments().getString(EXTRA_URL);
        }
    }

    @Override
    public void initView() {
        findView();
        init();
        configView();
        handlerErrorView();
        handlerUrl();
        handlerShareUrl();
        loadUrl();
    }

    @Override
    public void findView() {
        webview = rootView.findViewById(R.id.webview);
        webParentView = (LinearLayout) webview.getParent();
    }

    @Override
    public void init() {

    }

    @Override
    public void configView() {
        WebSettings webSettings = webview.getSettings();
        // 支持JS
        webSettings.setJavaScriptEnabled(true);
        // 设置JS是否可以打开WebView新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 可以加载本地html文件
        webSettings.setAllowFileAccess(true);
        // 设置页面是否支持缩放
        webSettings.setSupportZoom(false);
        // 这句解决本地跨域问题，如果你的 PDF 文件在站点里，是不需要的，但是，我们一般情况是加载站点外部 PDF 文件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
        }
        // 密码保存功能
        webSettings.setSavePassword(false);
        // 表单数据的保存功能
        webSettings.setSaveFormData(true);
        // 打开WebView的存储功能，这样, JS的localStorage,sessionStorage对象才可以使用
        webSettings.setDomStorageEnabled(true);
        // 缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 1.NARROW_COLUMNS：适应内容大小
        // 2.SINGLE_COLUMN：适应屏幕，内容将自动缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview.setWebChromeClient(new HybridChromeClient(mContext, webview, this));
        webview.setWebViewClient(new HybridViewClient(this));
    }

    @Override
    public void handlerErrorView() {
        errorView = new GeneralEmpty(mContext)
                .setEmptyLayoutGravity(Gravity.CENTER)
                .setEmptyImg(R.drawable.general_empty)
                .setEmptyTxtColor(R.color.color_999999)
                .setEmptyTxtSize(14)
                .setEmptyTxtStyle(Typeface.BOLD)
                .setEmptyBtnBackground(R.drawable.default_hybrid_btn_selector)
                .setEmptyBtnColor(R.color.white)
                .setEmptyBtn("刷新重试", new GeneralEmpty.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isError = false;
                        webview.reload();
                    }
                });
    }

    @Override
    public void handlerUrl() {

    }

    @Override
    public void handlerShareUrl() {

    }

    @Override
    public void loadUrl() {
        if (requestUrl == null) {
            return;
        }
        webview.loadUrl(requestUrl);
    }

    @Override
    public boolean modifyTitle() {
        return true;
    }

    @Override
    public boolean webCanGoBack() {
        return webview.canGoBack();
    }

    @Override
    public void monitorUrlLoading() {
        visibilityCloseImg(webCanGoBack());
    }

    @Override
    public void onProgressChangedBase(WebView view, int newProgress) {
        if (newProgress == 100) {
            monitorUrlLoading();
        }
    }

    @Override
    public void onReceivedTitleBase(WebView view, String title) {
        if (modifyTitle() && !TextUtils.isEmpty(title) && !webview.getUrl().contains(title)) {
            setTitleString(title);
        }
    }

    @Override
    public boolean shouldOverrideUrlLoadingBase(WebView view, String url) {
        /*处理打开支付宝*/
        if (new AlipayUrlIntercept().urlIntercept(mContext, url)) {
            return true;
        }
        /*处理文件*/
        if (new FileUrlIntercept().urlIntercept(mContext, url)) {
            return true;
        }
        /*处理打电话*/
        if (new TelUrlIntercept().urlIntercept(mContext, url)) {
            return true;
        }
        /*处理短链接*/
        if (new ShortLinkUrlIntercept().urlIntercept(mContext, url)) {
            return true;
        }
        /*普通加载*/
        view.loadUrl(url);
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoadingBase(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /*处理打开支付宝*/
            if (new AlipayUrlIntercept().urlIntercept(mContext, request.getUrl().toString())) {
                return true;
            }
            /*处理文件*/
            if (new FileUrlIntercept().urlIntercept(mContext, request.getUrl().toString())) {
                return true;
            }
            /*处理打电话*/
            if (new TelUrlIntercept().urlIntercept(mContext, request.getUrl().toString())) {
                return true;
            }
            /*处理短链接*/
            if (new ShortLinkUrlIntercept().urlIntercept(mContext, request.getUrl().toString())) {
                return true;
            }
            /*普通加载*/
            view.loadUrl(request.getUrl().toString());
        } else {
            /*处理打开支付宝*/
            if (new AlipayUrlIntercept().urlIntercept(mContext, request.toString())) {
                return true;
            }
            /*处理文件*/
            if (new FileUrlIntercept().urlIntercept(mContext, request.toString())) {
                return true;
            }
            /*处理打电话*/
            if (new TelUrlIntercept().urlIntercept(mContext, request.toString())) {
                return true;
            }
            /*处理短链接*/
            if (new ShortLinkUrlIntercept().urlIntercept(mContext, request.toString())) {
                return true;
            }
            /*普通加载*/
            view.loadUrl(request.toString());
        }
        return true;
    }

    @Override
    public void onPageStartedBase(WebView view, String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinishedBase(WebView view, String url) {
        if (!isError) {
            webParentView.removeAllViews();
            webParentView.addView(webview);
        }
    }

    @Override
    public void onReceivedSslErrorBase(WebView view, SslErrorHandler handler, SslError error) {
        /*接受所有网站的证书*/
        handler.proceed();
    }

    @Override
    public void onReceivedErrorBase(WebView view, WebResourceRequest request, WebResourceError error) {
        isError = true;
        webParentView.removeAllViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            webParentView.addView(((GeneralEmpty) errorView).setEmptyTxt(error.getDescription().toString()), view.getWidth(), view.getHeight());
        }
    }

    @Override
    public void onClickNav() {

    }

    @Override
    public void onClickBack() {
        if (webCanGoBack()) {
            webview.goBack();
        } else {
            mContext.onBackPressed();
        }
    }

    @Override
    public void onClickMore() {

    }

    @Override
    public void onClickClose() {
        mContext.onBackPressed();
    }

    @Override
    public boolean visibilityTitleLayout() {
        return true;
    }

    @Override
    public boolean visibilityBack() {
        return true;
    }

    @Override
    public boolean visibilityBackImg() {
        return true;
    }

    @Override
    public boolean visibilityBackTxt() {
        return false;
    }

    @Override
    public boolean visibilityCloseImg() {
        return false;
    }

    @Override
    public void visibilityCloseImg(boolean b) {
        if (b) {
            TitleBarWidget.getInstance().getNavCloseImg().setVisibility(View.VISIBLE);
        } else {
            TitleBarWidget.getInstance().getNavCloseImg().setVisibility(View.GONE);
        }
    }

    @Override
    public boolean visibilityMore() {
        return false;
    }

    @Override
    public boolean visibilityMoreImg() {
        return false;
    }

    @Override
    public boolean visibilityMoreTxt() {
        return false;
    }

    @Override
    public boolean visibilityDivider() {
        return true;
    }

    @Override
    public int setBgColor() {
        return 0;
    }

    @Override
    public int setAlpha() {
        return 255;
    }

    @Override
    public int setBackImg() {
        return 0;
    }

    @Override
    public int setCloseImg() {
        return 0;
    }

    @Override
    public int setBackTextInt() {
        return 0;
    }

    @Override
    public String setBackTextString() {
        return null;
    }

    @Override
    public void setBackTextString(String backStr) {
        TitleBarWidget.getInstance().getNavBackTxt().setText(backStr);
    }

    @Override
    public int setBackTextColor() {
        return 0;
    }

    @Override
    public int setBackTextSize() {
        return 0;
    }

    @Override
    public int setTitleInt() {
        return 0;
    }

    @Override
    public String setTitleString() {
        return null;
    }

    @Override
    public void setTitleString(String titleStr) {
        TitleBarWidget.getInstance().getNavTitle().setText(titleStr);
    }

    @Override
    public int setTitleColor() {
        return 0;
    }

    @Override
    public int setTitleSize() {
        return 0;
    }

    @Override
    public int setMoreImg() {
        return 0;
    }

    @Override
    public int setMoreTextInt() {
        return 0;
    }

    @Override
    public String setMoreTextString() {
        return null;
    }

    @Override
    public void setMoreTextString(String moreStr) {
        TitleBarWidget.getInstance().getNavMoreTxt().setText(moreStr);
    }

    @Override
    public int setMoreTextColor() {
        return 0;
    }

    @Override
    public int setMoreTextSize() {
        return 0;
    }

    @Override
    public int setDividerColor() {
        return 0;
    }
}
