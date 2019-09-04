package com.wonly.lib_common.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wonly.lib_base.base.BaseDialog;
import com.wonly.lib_common.R;
import com.wonly.lib_common.databinding.DialogCommonNormalBinding;

/**
 * @Author: HSL
 * @Time: 2019/9/4 10:28
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class DefaultDialog extends BaseDialog {

    private DialogCommonNormalBinding mBinding;

    public DefaultDialog(Context context) {
        super(context, 0.4f, Gravity.CENTER);
    }

    @Override
    protected void initAttribute(Window window, WindowManager.LayoutParams layoutParams) {
        super.initAttribute(window, layoutParams);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void initLayout(Context context) {
        mBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.dialog_common_normal, null, false);
        setContentView(mBinding.getRoot());
    }

    /**
     * 标题
     *
     * @param title
     */
    public void setTitleText(String title) {
        if (title != null) {
            mBinding.titleTv.setText(title);
        }
    }

    /**
     * 标题
     *
     * @param color
     */
    public void setTitleColor(String color) {
        if (color != null) {
            mBinding.titleTv.setTextColor(Color.parseColor(color));
        }
    }

    /**
     * 内容
     *
     * @param content
     */
    public void setContentText(String content) {
        if (content != null) {
            mBinding.contentTv.setText(content);
        }
    }

    /**
     * 内容
     *
     * @param color
     */
    public void setContentColor(String color) {
        if (color != null) {
            mBinding.contentTv.setTextColor(Color.parseColor(color));
        }
    }

    /**
     * 取消
     *
     * @param cancel
     */
    public void setCancelText(String cancel) {
        if (cancel != null) {
            mBinding.cancelTv.setText(cancel);
        }
    }

    /**
     * 取消
     *
     * @param color
     */
    public void setCancelColor(String color) {
        if (color != null) {
            mBinding.cancelTv.setTextColor(Color.parseColor(color));
        }
    }


    /**
     * 确定
     *
     * @param confirm
     */
    public void setConfirmText(String confirm) {
        if (confirm != null) {
            mBinding.confirmTv.setText(confirm);
        }
    }

    /**
     * 确定
     *
     * @param color
     */
    public void setConfirmColor(String color) {
        if (color != null) {
            mBinding.confirmTv.setTextColor(Color.parseColor(color));
        }
    }

    /**
     * 显示单个按钮
     *
     * @param onlyShowConfirm
     */
    public void showSingleButton(boolean onlyShowConfirm) {
        mBinding.splitView.setVisibility(View.GONE);
        if (onlyShowConfirm) {
            mBinding.confirmTv.setVisibility(View.VISIBLE);
            mBinding.cancelTv.setVisibility(View.GONE);
        } else {
            mBinding.confirmTv.setVisibility(View.GONE);
            mBinding.cancelTv.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 默认按钮显示
     */
    public void showDefaultButton() {
        mBinding.splitView.setVisibility(View.VISIBLE);
        mBinding.confirmTv.setVisibility(View.VISIBLE);
        mBinding.cancelTv.setVisibility(View.VISIBLE);
    }

    /**
     * 取消
     */
    public void onCancelTvClicked() {
        if (mOnDialogCancelListener != null) {
            mOnDialogCancelListener.onClickCancel();
        } else {
            dismiss();
        }
    }

    /**
     * 确认
     */
    public void onConfirmTvClicked() {
        if (mOnDialogConfirmListener != null) {
            mOnDialogConfirmListener.onClickConfirm();
        }
    }

    /**
     * Interface 确认
     */
    public interface OnDialogConfirmListener {

        /**
         * 确认
         */
        void onClickConfirm();
    }

    private OnDialogConfirmListener mOnDialogConfirmListener;

    public void setOnDialogConfirmListener(OnDialogConfirmListener onDialogConfirmListener) {
        mOnDialogConfirmListener = onDialogConfirmListener;
    }

    /**
     * Interface 取消
     */
    public interface OnDialogCancelListener {

        /**
         * 取消
         */
        void onClickCancel();
    }

    private OnDialogCancelListener mOnDialogCancelListener;

    public void setOnDialogCancelListener(OnDialogCancelListener onDialogCancelListener) {
        mOnDialogCancelListener = onDialogCancelListener;
    }
}
