package com.wonly.intellect.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wonly.intellect.R;
import com.wonly.intellect.databinding.MergeMainTabBinding;
import com.wonly.intellect.ui.main.DefaultMainFragment;
import com.wonly.lib_base.utils.SPUtils;
import com.wonly.lib_common.router.HomePath;
import com.wonly.lib_common.router.UserPath;
import com.wonly.lib_common.service.IHomeModuleService;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.module_main.widget
 * @Author: HSL
 * @Time: 2019/08/21 17:39
 * @E-mail: xxx@163.com
 * @Description: 主页Tab
 */
public class MainTabLayout extends LinearLayout {

    public static final int HOME_TAB_INDEX = 0;
    public static final int MINE_TAB_INDEX = 1;
    public static final String CURRENT_TAB_INDEX = "current_tab_index";

    private MergeMainTabBinding mTabBinding;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mCurrentFragment;
    private Fragment mHomeFragment;
    private Fragment mMineFragment;
    private int mCurrentTabIndex = -1;
    private int mDefaultShowTabIndex;

    public MainTabLayout(Context context) {
        this(context, null);
    }

    public MainTabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTabBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.merge_main_tab, this, true);
        mTabBinding.homeTabLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabHomeClick();
            }
        });
        mTabBinding.mineTabLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabMineClick();
            }
        });
    }

    /**
     * 首页
     * 点击事件
     */
    public void onTabHomeClick() {
        if (mCurrentTabIndex == HOME_TAB_INDEX) {
            //避免重复点击，可以做一些其他操作
            IHomeModuleService moduleService = ARouter.getInstance().navigation(IHomeModuleService.class);
            if (moduleService != null) {
                moduleService.refreshHomeFragment("来自首页刷新!");
            }
            return;
        }
        switchStatusBarColor(HOME_TAB_INDEX);
        switchTabStatus(HOME_TAB_INDEX);
        if (mHomeFragment == null) {
            mHomeFragment = (Fragment) ARouter.getInstance().build(HomePath.FRAG_HOME_HOME).navigation();
        }
        if (mHomeFragment == null) {
            //防止模块化切换时，为Null
            mHomeFragment = DefaultMainFragment.newInstance("首页");
        }
        showHideFragment(mHomeFragment, HOME_TAB_INDEX);
        mCurrentTabIndex = HOME_TAB_INDEX;
        mCurrentFragment = mHomeFragment;
    }

    /**
     * 我的
     * 点击事件
     */
    public void onTabMineClick() {
        if (mCurrentTabIndex == MINE_TAB_INDEX) {
            //避免重复点击，可以做一些其他操作
            return;
        }
        switchStatusBarColor(MINE_TAB_INDEX);
        switchTabStatus(MINE_TAB_INDEX);
        if (mMineFragment == null) {
            //ARouter获取用户模块下的Fragment
            mMineFragment = (Fragment) ARouter.getInstance().build(UserPath.FRAG_USER_HOME).navigation();
        }
        if (mMineFragment == null) {
            mMineFragment = DefaultMainFragment.newInstance("我的");
        }
        showHideFragment(mMineFragment, MINE_TAB_INDEX);
        mCurrentTabIndex = MINE_TAB_INDEX;
        mCurrentFragment = mMineFragment;
    }

    /**
     * 设置默认显示的下标
     *
     * @param index
     */
    public void setDefaultShowFragment(int index) {
        mDefaultShowTabIndex = index;
    }

    /**
     * 初始化Fragment
     *
     * @param savedInstanceState
     */
    public void initFragment(Bundle savedInstanceState) {
        if (mFragmentManager == null) {
            // TODO: 2019/8/22  向下转型存在风险
            mFragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        }
        if (savedInstanceState == null) {
            //默认显示Tab
            if (mDefaultShowTabIndex == HOME_TAB_INDEX) {
                onTabHomeClick();
            } else {
                onTabMineClick();
            }
        } else {
            int lastTabIndex = savedInstanceState.getInt(CURRENT_TAB_INDEX, 0);
            restoreFragment(lastTabIndex);
        }
    }

    /**
     * 在内存重启等情况下
     * 恢复Fragment
     * 避免页面重复(24.0.0以后官方修复了Fragment无法保存显示、隐藏状态的问题，此问题不存在)
     * 此处一旦恢复，内存重启之前的Fragment页恢复了
     * 如果在不做以下处理，就会重新initFragment，那么就会Add和显示一个新的Fragment，造成两个Fragment同时在show，
     * 那么会永远显示先show的（即恢复过来的Fragment，英文他本来就是show的，哈哈~）
     * 所以，遇到这种情况，我们直接复用store的Fragment即可~
     */
    private void restoreFragment(int lastTabIndex) {
        //内存重启之前Showing的Fragment
        Fragment lastFragment = null;
        mHomeFragment = mFragmentManager
                .findFragmentByTag(makeTabTag(HOME_TAB_INDEX));
        mMineFragment = mFragmentManager
                .findFragmentByTag(makeTabTag(MINE_TAB_INDEX));
        if (lastTabIndex == HOME_TAB_INDEX) {
            lastFragment = mHomeFragment;
        } else if (lastTabIndex == MINE_TAB_INDEX) {
            lastFragment = mMineFragment;
        }
        switchTabStatus(lastTabIndex);
        mCurrentTabIndex = lastTabIndex;
        mCurrentFragment = lastFragment;
    }

    /**
     * 显示目标Fragment
     * 隐藏当前Fragment
     *
     * @param fragment 将要显示的Fragment
     * @param position 将要显示的Fragment的位置
     */
    private void showHideFragment(Fragment fragment, int position) {
        if (mFragmentTransaction == null) {
            mFragmentTransaction = mFragmentManager.beginTransaction();
        }
        //通过isAdded()这个方法判断Fragment是否被add可能并不准确
        //所以设置Tag双重判断
        //避免Fragment already added问题
        Fragment isAddingFragment = mFragmentManager.findFragmentByTag(makeTabTag(position));
        if (isAddingFragment == null && !fragment.isAdded() && fragment != null) {
            mFragmentTransaction.add(R.id.main_container_fl, fragment, makeTabTag(position));
        }
        if (mCurrentFragment != null) {
            mFragmentTransaction.hide(mCurrentFragment);
        }
        mFragmentTransaction.show(fragment);
        mFragmentTransaction.commitAllowingStateLoss();
        mFragmentTransaction = null;
    }

    private String makeTabTag(int tabIndex) {
        return String.format("maintab_%s", tabIndex);
    }

    /**
     * 切换Tab状态：文本 ICON
     *
     * @param position
     */
    private void switchTabStatus(int position) {
        //选中处理
        int selectedColor = ContextCompat.getColor(getContext(), R.color.mediumorchid);
        if (position == HOME_TAB_INDEX) {
            mTabBinding.homeTabTv.setTextColor(selectedColor);
            mTabBinding.homeTabIv.setImageResource(R.drawable.tab_home_selected_icon);
        } else if (position == MINE_TAB_INDEX) {
            mTabBinding.mineTabTv.setTextColor(selectedColor);
            mTabBinding.mineTabIv.setImageResource(R.drawable.tab_mine_selected_icon);
        }
        //恢复默认状态
        int defaultColor = ContextCompat.getColor(getContext(), R.color._9);
        if (mCurrentTabIndex == HOME_TAB_INDEX) {
            mTabBinding.homeTabTv.setTextColor(defaultColor);
            mTabBinding.homeTabIv.setImageResource(R.drawable.tab_home_normal_icon);
        } else if (mCurrentTabIndex == MINE_TAB_INDEX) {
            mTabBinding.mineTabTv.setTextColor(defaultColor);
            mTabBinding.mineTabIv.setImageResource(R.drawable.tab_mine_normal_icon);
        }
    }

    /**
     * 切换状态栏背景
     *
     * @param position
     */
    private void switchStatusBarColor(int position) {
        if (position == HOME_TAB_INDEX) {
//            ImmersionBar.with(this)
//                    .keyboardEnable(false)
//                    .statusBarDarkFont(false)
//                    .statusBarColor(R.color.transparent)
//                    .init();
        } else {
//            ImmersionBar.with(this)
//                    .keyboardEnable(false)
//                    .statusBarDarkFont(false)
//                    .statusBarColor(R.color.transparent)
//                    .init();
        }
    }

    /**
     * 根据下标切换Tab
     *
     * @param index
     */
    public void switchMainTab(int index) {
        if (index == HOME_TAB_INDEX) {
            onTabHomeClick();
        } else {
            onTabMineClick();
        }
    }

    public void saveCurrentTabIndex() {
        SPUtils.getInstance().putInt(CURRENT_TAB_INDEX, mCurrentTabIndex);
    }
}
