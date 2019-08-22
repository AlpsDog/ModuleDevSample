package com.wonly.module_user.ui.mine;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonly.lib_base.base.BaseFragment;
import com.wonly.lib_common.router.UserPath;
import com.wonly.module_user.R;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:42
 * @E-mail: xxx@163.com
 * @Description: 我的~
 */
@Route(path = UserPath.FRAG_USER_HOME)
public class MineFragment extends BaseFragment {


    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_fragment_mine, container, false);
    }

}
