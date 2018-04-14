package com.qingmang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.moudle.entity.BankCard;

import java.util.List;

/**
 * Created by xiejingbao on 2018/3/9.
 */

public class BankCardAdapter extends BaseQuickAdapter<BankCard, BaseViewHolder> {


    public BankCardAdapter(@Nullable List<BankCard> data) {
        super(R.layout.bank_card_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCard item) {
//        ImageLoaderUtil.getInstance().showImage(item.getLogo(),(ImageView) helper.getView(R.id.iv_bank),0);
//
//        helper.setText(R.id.tv_name,item.getName());
       helper.setText(R.id.tv_bank_name,item.getBankName());
       helper.setText(R.id.tv_card_number,item.getCreditCode().substring(item.getCreditCode().length()-4,
               item.getCreditCode().length()));

    }


}
