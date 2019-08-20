package com.wonly.module_home.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonly.module_home.R;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:31
 * @E-mail: xxx@163.com
 * @Description: 首页~
 */
public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment_home, container, false);
    }

}
