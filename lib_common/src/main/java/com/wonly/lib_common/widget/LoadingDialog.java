package com.wonly.lib_common.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.Window;
import android.view.WindowManager;

import com.wonly.lib_base.base.BaseDialog;
import com.wonly.lib_base.utils.ObjectUtils;
import com.wonly.lib_common.R;
import com.wonly.lib_common.databinding.DialogNetLoadingBinding;

/**
 * @Author: HSL
 * @Time: 2019/9/4 9:31
 * @E-mail: xxx@163.com
 * @Description: 网络加载等待时的dialog
 */
public class LoadingDialog extends BaseDialog {

    private DialogNetLoadingBinding mBinding;

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected void initAttribute(Window window, WindowManager.LayoutParams layoutParams) {
//        int width = (int) mContext.getResources().getDimension(R.dimen.spacing_260);
//        int height = (int) mContext.getResources().getDimension(R.dimen.spacing_160);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
//        setContentView(content, layoutParams);
        //触摸dialog之外不消失
        setCanceledOnTouchOutside(false);
        window.setDimAmount(0f);
        window.setWindowAnimations(R.style.dialog_no_anim);
    }

    @Override
    protected void initLayout(Context context) {
        mBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.dialog_net_loading, null, false);
        setContentView(mBinding.getRoot());
    }

    public void showLoading() {
        showDialog();
    }

    /**
     * @param msg
     */
    public void showLoading(String msg) {
        setMessage(msg);
        showDialog();
    }

    /**
     * 设置提示
     *
     * @param msg
     */
    public void setMessage(CharSequence msg) {
        if (!ObjectUtils.isEmpty(msg)) {
            mBinding.loadingDialogMessage.setText(msg);
        }
    }
}
