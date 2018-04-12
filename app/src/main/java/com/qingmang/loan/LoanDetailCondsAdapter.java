package com.qingmang.loan;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangpw
 * on 2018/4/12 16:50
 */
public class LoanDetailCondsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private static final Map<String, String> CONS;
    private int sort = 1;

    static {
        CONS = new HashMap<>();
        CONS.put("OV18", "年满18周岁以上");
        CONS.put("OV22UN60", "年满22周岁以上，60周岁以下");
        CONS.put("FIXJOB", "有固定工作");
        CONS.put("MORTGAGED", "有车，有房可抵押");
        CONS.put("CREDIT", "有信用记录");
        CONS.put("REAL", "手机实名认证");
    }

    public LoanDetailCondsAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_loan_detail_cons_name, sort++ + ". " + CONS.get(item));
    }
}
