package com.wonly.module_home.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wonly.module_home.R;
import com.wonly.module_home.ui.home.HomeFragment;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:23
 * @E-mail: xxx@163.com
 * @Description: 独立模块时，启动界面~
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_home);
        getSupportFragmentManager().beginTransaction().add(R.id.home_container_fl, HomeFragment.newInstance()).commit();
    }
}
