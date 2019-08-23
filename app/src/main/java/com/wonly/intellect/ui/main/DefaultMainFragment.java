package com.wonly.intellect.ui.main;


import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonly.intellect.R;
import com.wonly.intellect.databinding.FragmentDefaultMainBinding;
import com.wonly.lib_base.base.BaseFragment;

/**
 * @Author: HSL
 * @Time: 2019/8/23 8:53
 * @E-mail: xxx@163.com
 * @Description: 独立运行时，默认Fragment~
 */
public class DefaultMainFragment extends BaseFragment {

    public static final String PARAM = "param";
    private FragmentDefaultMainBinding mBinding;
    private Activity mActivity;
    private String mName;

    public DefaultMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(PARAM, "default");
        }
    }

    public static DefaultMainFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString(PARAM, name);
        DefaultMainFragment fragment = new DefaultMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_default_main, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.fragmentNameTv.setText(mName);
    }
}
