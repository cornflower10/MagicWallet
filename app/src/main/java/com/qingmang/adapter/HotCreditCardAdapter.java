package com.qingmang.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.moudle.entity.CreditCard;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.util.List;

/**
 * Created by xiejingbao on 2018/3/9.
 */

public class HotCreditCardAdapter extends BaseQuickAdapter<CreditCard, BaseViewHolder> {


    public HotCreditCardAdapter(@Nullable List<CreditCard> data) {
        super(R.layout.hot_credit_card_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditCard item) {
        ImageLoaderUtil.getInstance().showImage(item.getLogo(),(ImageView) helper.getView(R.id.iv_card),0);
        String[] str = item.getTags().split(",");
        helper.setText(R.id.tv_peoples,item.getNumber()+"人已申请");
        helper.setText(R.id.tv_card_name,item.getBankName());
        helper.setText(R.id.tv_date, item.getFreePeriod()+"天免息期");
        try {
            helper.setText(R.id.tv_des, str[0]);
            helper.setText(R.id.tv_amount, str[1]);
        }catch (Exception e){
            LogManager.e("Exception",e);
        }

    }


}
