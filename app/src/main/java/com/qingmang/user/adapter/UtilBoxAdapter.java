package com.qingmang.user.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.moudle.entity.UtilBox;

import java.util.List;

/**
 * Created by xiejingbao on 2018/3/9.
 */

public class UtilBoxAdapter extends BaseQuickAdapter<UtilBox, BaseViewHolder> {


    public UtilBoxAdapter(@Nullable List<UtilBox> data) {
        super(R.layout.util_box_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UtilBox item) {
         helper.setText(R.id.tv_name,item.getName());
         helper.setImageResource(R.id.iv,item.getRes());
    }


}
