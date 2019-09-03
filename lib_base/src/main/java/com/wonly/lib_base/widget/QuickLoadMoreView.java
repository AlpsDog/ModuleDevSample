package com.wonly.lib_base.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.wonly.lib_base.R;

/**
 * @Author: HSL
 * @Time: 2019/4/15 12:59
 * @E-mail: xxx@163.com
 * @Description: 自定义BaseQuickAdapter加载更多显示的View
 */
public class QuickLoadMoreView extends LoadMoreView {

    /**
     * 自定义布局
     *
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.fl_quick_load_more;
    }

    /**
     * 加载中
     *
     * @return
     */
    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    /**
     * 加载失败
     *
     * @return
     */
    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    /**
     * 全部加载完毕
     *
     * @return
     */
    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
