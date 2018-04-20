package com.qingmang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;

import java.util.List;

/**
 * Created by xiejingbao on 2018/3/9.
 */

public class StringAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public StringAdapter(@Nullable List<String> data) {
        super(R.layout.string_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
       helper.setText(R.id.tv_name,item);

    }


}
