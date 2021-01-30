package com.luffy.hybrid.view.error;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luffy.hybrid.R;

/**
 * Created by lvlufei on 2019/8/27
 *
 * @name 异常视图
 * @desc
 */
public class HybridErrorView extends BaseCombinationView {

    LinearLayout errorRoot;
    ImageView errorImg;
    TextView errorTxt;
    TextView errorBtn;

    public HybridErrorView(Context context) {
        super(context);
    }

    public HybridErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setLayoutView() {
        return R.layout.hybrid_error_layout;
    }

    @Override
    public void initView() {
        errorRoot = findViewById(R.id.error_root);
        errorImg = findViewById(R.id.error_img);
        errorTxt = findViewById(R.id.error_txt);
        errorBtn = findViewById(R.id.error_btn);
    }

    @Override
    public void initAttrs(AttributeSet attrs) {

    }

    @Override
    public void bindAttrs() {

    }

    public HybridErrorView setErrorRootBackground(int resId) {
        errorRoot.setVisibility(View.VISIBLE);
        errorRoot.setBackgroundResource(resId);
        return this;
    }

    public HybridErrorView setErrorRootGravity(int gravity) {
        errorRoot.setVisibility(View.VISIBLE);
        errorRoot.setGravity(gravity);
        return this;
    }

    public HybridErrorView setErrorRootPadding(int top) {
        errorRoot.setVisibility(View.VISIBLE);
        errorRoot.setPadding(0, top, 0, 0);
        return this;
    }

    public HybridErrorView setErrorImg(int imgId) {
        errorImg.setVisibility(View.VISIBLE);
        errorImg.setImageResource(imgId);
        return this;
    }

    public HybridErrorView setErrorImgMargin(int left, int top, int right, int bottom) {
        errorImg.setVisibility(View.VISIBLE);
        LayoutParams mLayoutParams = (LayoutParams) errorImg.getLayoutParams();
        mLayoutParams.setMargins(left, top, right, bottom);
        return this;
    }

    public HybridErrorView setErrorTxt(int txtId) {
        errorTxt.setVisibility(View.VISIBLE);
        errorTxt.setText(txtId);
        return this;
    }

    public HybridErrorView setErrorTxt(String txtStr) {
        errorTxt.setVisibility(View.VISIBLE);
        errorTxt.setText(txtStr);
        return this;
    }

    public HybridErrorView setErrorTxtSize(float size) {
        errorTxt.setVisibility(View.VISIBLE);
        errorTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    public HybridErrorView setErrorTxtColor(int color) {
        errorTxt.setVisibility(View.VISIBLE);
        errorTxt.setTextColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public HybridErrorView setErrorTxtStyle(int style) {
        errorTxt.setVisibility(View.VISIBLE);
        errorTxt.setTypeface(Typeface.defaultFromStyle(style));
        return this;
    }

    public HybridErrorView setErrorTxtGravity(int gravity) {
        errorTxt.setVisibility(View.VISIBLE);
        errorTxt.setGravity(gravity);
        return this;
    }

    public HybridErrorView setErrorTxtMargin(int left, int top, int right, int bottom) {
        errorTxt.setVisibility(View.VISIBLE);
        LayoutParams mLayoutParams = (LayoutParams) errorTxt.getLayoutParams();
        mLayoutParams.setMargins(left, top, right, bottom);
        return this;
    }

    public HybridErrorView setErrorBtnSize(float size) {
        errorBtn.setVisibility(View.VISIBLE);
        errorBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    public HybridErrorView setErrorBtnColor(int color) {
        errorBtn.setVisibility(View.VISIBLE);
        errorBtn.setTextColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public HybridErrorView setErrorBtnStyle(int style) {
        errorBtn.setVisibility(View.VISIBLE);
        errorBtn.setTypeface(Typeface.defaultFromStyle(style));
        return this;
    }

    public HybridErrorView setErrorBtnBackground(int resId) {
        errorBtn.setVisibility(View.VISIBLE);
        errorBtn.setBackgroundResource(resId);
        return this;
    }

    public HybridErrorView setErrorBtnWidthHeight(int width, int height) {
        errorBtn.setVisibility(View.VISIBLE);
        if (width == 0 && height == 0) {
            errorBtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        } else {
            errorBtn.setLayoutParams(new LayoutParams(width, height));
        }
        return this;
    }

    public HybridErrorView setErrorBtnMargin(int left, int top, int right, int bottom) {
        errorBtn.setVisibility(View.VISIBLE);
        LayoutParams mLayoutParams = (LayoutParams) errorBtn.getLayoutParams();
        mLayoutParams.setMargins(left, top, right, bottom);
        return this;
    }

    public HybridErrorView setErrorBtnPadding(int left, int top, int right, int bottom) {
        errorBtn.setVisibility(View.VISIBLE);
        errorBtn.setPadding(left, top, right, bottom);
        return this;
    }

    public HybridErrorView setErrorBtnPadding(int padding) {
        errorBtn.setVisibility(View.VISIBLE);
        errorBtn.setPadding(padding, padding, padding, padding);
        return this;
    }

    public HybridErrorView setErrorBtn(int resId, final OnClickListener mOnClickListener) {
        errorBtn.setVisibility(View.VISIBLE);
        errorBtn.setText(resId);
        errorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(errorBtn);
                }
            }
        });
        return this;
    }

    public HybridErrorView setErrorBtn(String resStr, final OnClickListener mOnClickListener) {
        errorBtn.setVisibility(View.VISIBLE);
        errorBtn.setText(resStr);
        errorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(errorBtn);
                }
            }
        });
        return this;
    }

    public View show() {
        return mView;
    }

    public interface OnClickListener {
        void onClick(View v);
    }
}
