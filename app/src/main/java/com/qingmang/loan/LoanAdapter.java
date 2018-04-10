package com.qingmang.loan;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.loan.entity.LoanListContent;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.util.List;

/**
 * Created by jiangpw
 * on 2018/4/10 14:02
 */
public class LoanAdapter extends BaseQuickAdapter<LoanListContent, BaseViewHolder> {
    public LoanAdapter(int layoutResId, @Nullable List<LoanListContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoanListContent item) {
        helper.setText(R.id.tv_loan_name, item.getName());
        helper.setText(R.id.tv_low_cost_value, item.getRateLower() + "%");
        helper.setText(R.id.tv_highest_value, item.getLoanUpper() / 10000 + "");

        ImageLoaderUtil.getInstance().showImage(item.getLogo(), (ImageView) helper.getView(R.id.iv_icon_loan), R.mipmap.ic_launcher_round);
    }
}
