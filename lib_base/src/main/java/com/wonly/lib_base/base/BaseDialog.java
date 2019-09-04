package com.wonly.lib_base.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.wonly.lib_base.R;


public abstract class BaseDialog extends Dialog {

    protected LayoutParams mLayoutParams;
    protected LayoutInflater mLayoutInflater;

    /**
     * 默认样式
     *
     * @param context
     */
    public BaseDialog(Context context) {
        this(context, R.style.base_dialog);
    }

    /**
     * 自定义样式
     *
     * @param context
     * @param themeResId
     */
    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        // 默认0.6,居中
        initBaseDialog(context, 0.6f, Gravity.CENTER);
    }

    /**
     * 默认样式
     * 指定内容区域外的透明度
     * Dialog位置
     *
     * @param context
     * @param alpha   设置内容区域以外的背景 0.0f--1f(不透明)
     * @param gravity 方向(Gravity.BOTTOM,Gravity.TOP,Gravity.LEFT,Gravity.RIGHT)
     */
    public BaseDialog(Context context, float alpha, int gravity) {
        super(context, R.style.base_dialog);
        initBaseDialog(context, alpha, gravity);
    }

    private void initBaseDialog(Context context, float alpha, int gravity) {
        mLayoutInflater = LayoutInflater.from(getContext());
        Window window = this.getWindow();
        mLayoutParams = window.getAttributes();
        window.setDimAmount(alpha);
        window.setAttributes(mLayoutParams);
        if (mLayoutParams != null) {
            mLayoutParams.gravity = gravity;
        }
        //用于设置dialog属性
        initAttribute(window, mLayoutParams);
        //用于初始化布局、控件
        initLayout(context);
    }

    /**
     * 用于设置Dialog的一些属性
     * 如：位置、偏移量、动画、尺寸等~
     *
     * @param window
     * @param layoutParams
     */
    protected void initAttribute(Window window, LayoutParams layoutParams) {

    }


    /**
     * 用于加载布局，初始化控件
     *
     * @param context
     */
    protected abstract void initLayout(Context context);

    /**
     * 设置全屏显示
     */
    public void setFullScreen() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams lp = window.getAttributes();
        lp.width = LayoutParams.MATCH_PARENT;
        lp.height = LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        //全屏时，状态栏为亮色，防止黑色背景
        setStatusBarLightMode(window, true);
    }

    /**
     * 设置宽度match_parent
     */
    public void setFullScreenWidth() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams lp = window.getAttributes();
        lp.width = LayoutParams.MATCH_PARENT;
        lp.height = LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    /**
     * 设置高度为match_parent
     */
    public void setFullScreenHeight() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams lp = window.getAttributes();
        lp.width = LayoutParams.WRAP_CONTENT;
        lp.height = LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    /**
     * 获取LayoutParams便于根据需求设置
     * 相对于对于原始位置的偏移.
     *
     * @return
     */
    public LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    public void setOnWhole() {
        getWindow().setType(LayoutParams.TYPE_SYSTEM_ALERT);
    }

    /**
     * show dialog
     */
    public void showDialog() {
        show();
    }

    /**
     * 设置状态栏是否为浅色模式
     *
     * @param window      The window.
     * @param isLightMode True to set status bar light mode, false otherwise.
     */
    public void setStatusBarLightMode(@NonNull final Window window,
                                      final boolean isLightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (isLightMode) {
                    window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }
}
