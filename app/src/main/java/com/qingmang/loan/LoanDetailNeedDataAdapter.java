package com.qingmang.loan;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;

import java.util.List;

/**
 * Created by jiangpw
 * on 2018/4/12 16:50
 */
public class LoanDetailNeedDataAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int sort = 1;

    public LoanDetailNeedDataAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_loan_detail_cons_name, sort++ + ". " + item);
    }
}
