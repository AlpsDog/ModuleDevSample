package com.wonly.lib_base.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wonly.lib_base.utils.ObjectUtils;

import java.util.List;

/**
 * @Author: HSL
 * @Time: 2019/4/23 17:05
 * @E-mail: xxx@163.com
 * @Description: ListView适配器~
 */
public class BaseListAdapter<T, BT extends ViewDataBinding> extends BaseAdapter {

    private Context context;
    private List<T> dataList;
    /**
     * 列表项布局id
     */
    private int layoutItemId;
    /**
     * item 对象id
     */
    private int variableId;

    public BaseListAdapter(Context context, List<T> dataList, int layoutItemId, int variableId) {
        this.context = context;
        this.dataList = dataList;
        this.layoutItemId = layoutItemId;
        this.variableId = variableId;
    }

    protected void getItemView(int position, BT binding) {

    }

    @Override
    public int getCount() {
        return ObjectUtils.isEmpty(dataList) ? 0 : dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            BT binding;
            if (convertView == null) {
                binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutItemId, parent, false);
            } else {
                binding = DataBindingUtil.getBinding(convertView);
            }
            binding.setVariable(variableId, dataList.get(position));
            getItemView(position, binding);
            return binding.getRoot();
        } catch (Exception e) {
            // TODO: 2018/9/28
        }
        return convertView;
    }

    public List<T> getDataList() {
        return this.dataList;
    }
}
