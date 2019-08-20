package com.wonly.module_user.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wonly.module_user.R;
import com.wonly.module_user.ui.mine.MineFragment;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:43
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class MineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_mine);
        getSupportFragmentManager().beginTransaction().add(R.id.mine_container_fl, MineFragment.newInstance()).commit();
    }
}
