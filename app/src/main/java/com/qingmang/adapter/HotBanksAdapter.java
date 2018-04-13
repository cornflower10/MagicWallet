package com.qingmang.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.moudle.entity.Bank;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.util.List;

/**
 * Created by xiejingbao on 2018/3/9.
 */

public class HotBanksAdapter extends BaseQuickAdapter<Bank, BaseViewHolder> {


    public HotBanksAdapter(@Nullable List<Bank> data) {
        super(R.layout.hot_banks_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bank item) {
        ImageLoaderUtil.getInstance().showImage(item.getLogo(),(ImageView) helper.getView(R.id.iv_bank),0);

        helper.setText(R.id.tv_name,item.getName());

    }


}
