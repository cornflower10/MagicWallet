package com.qingmang.loan;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.loan.entity.ProsList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangpw
 * on 2018/4/12 16:51
 */
public class LoanDetailProcessAdapter extends BaseQuickAdapter<ProsList, BaseViewHolder> {

    private static final Map<String, Integer> PROCESS;

    static {
        PROCESS = new HashMap<>();
        PROCESS.put("ELI", R.drawable.apply_step1);
        PROCESS.put("IYA", R.drawable.apply_step2);
        PROCESS.put("FER", R.drawable.apply_step10);
        PROCESS.put("ORA", R.drawable.apply_step4);
        PROCESS.put("SEE", R.drawable.apply_step8);
        PROCESS.put("ECA", R.drawable.apply_step7);
        PROCESS.put("SLE", R.drawable.apply_step6);
        PROCESS.put("CYI", R.drawable.apply_step5);
        PROCESS.put("BCC", R.drawable.apply_step3);
        PROCESS.put("CCC", R.drawable.apply_step3);
        PROCESS.put("CTR", R.drawable.apply_step9);
    }

    public LoanDetailProcessAdapter(int layoutResId, @Nullable List<ProsList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProsList item) {
        helper.setText(R.id.tv_loan_detail_process_name, item.getName());
        Glide.with(mContext).load(PROCESS.get(item.getCode())).into((ImageView) helper.getView(R.id.iv_loan_detail_process_icon));
    }
}
