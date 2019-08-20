package com.wonly.module_user.ui.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonly.module_user.R;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:42
 * @E-mail: xxx@163.com
 * @Description: 我的~
 */
public class MineFragment extends Fragment {


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
