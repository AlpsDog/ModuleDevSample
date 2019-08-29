package com.wonly.intellect.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wonly.intellect.R;
import com.wonly.intellect.databinding.FragmentDefaultMainBinding;
import com.wonly.lib_base.base.BaseFragment;

/**
 * @Author: HSL
 * @Time: 2019/8/23 8:53
 * @E-mail: xxx@163.com
 * @Description: 独立运行时，默认Fragment~
 */
public class DefaultMainFragment extends BaseFragment<FragmentDefaultMainBinding> {

    public static final String PARAM = "param";
    private String mName;

    public DefaultMainFragment() {
        // Required empty public constructor
    }

    public static DefaultMainFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString(PARAM, name);
        DefaultMainFragment fragment = new DefaultMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initExtra(Bundle bundle) {
        mName = bundle.getString(PARAM, "default");
    }

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_default_main;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBinding.fragmentNameTv.setText(mName);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }
}
