package com.qingmang.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.baselibrary.utils.AmountUtils;
import com.qingmang.baselibrary.utils.DateUtils;
import com.qingmang.moudle.entity.Loan;

import java.util.List;

/**
 * Created by xiejingbao on 2018/3/9.
 */

public class CreditCardOrderAdapter extends BaseQuickAdapter<Loan.ContentBean, BaseViewHolder> {


    public CreditCardOrderAdapter(@Nullable List<Loan.ContentBean> data) {
        super(R.layout.credit_card_order_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Loan.ContentBean item) {
        //wait批准中，active申请成功，fail申请失败
        helper.setText(R.id.tv_no,item.getOrderno());
        helper.setText(R.id.tv_time, DateUtils.transferLongToDateHM(item.getCreateTime()));
        helper.setText(R.id.tv_amount, AmountUtils.amountFormat(item.getApply())+"元");
        helper.setText(R.id.tv_bank_no, item.getIdCard());
        TextView textView =  ((TextView)helper.getView(R.id.tv_status));
        if("wait".equals(item.getState())){
            helper.setText(R.id.tv_status, "批准中");
            helper.setText(R.id.tv_name, "批准金额：");
            helper.setText(R.id.tv_reason, "审批中");
            textView.setCompoundDrawables(
                    ContextCompat.getDrawable(mContext,R.drawable.icon_wait),null,null,null);
            helper.setVisible(R.id.rl_delete,false);
        }else if("active".equals(item.getState())){
            helper.setText(R.id.tv_status, "申请成功");
            textView.setCompoundDrawables(
                    ContextCompat.getDrawable(mContext,R.drawable.icon_ok),null,null,null);
            helper.setText(R.id.tv_name, "批准金额：");
            helper.setText(R.id.tv_reason, AmountUtils.amountFormat(item.getFinalAmount())+"元");
            helper.setVisible(R.id.rl_delete,false);
        }
        else if("fail".equals(item.getState())){
            helper.setText(R.id.tv_status, "申请失败");
            textView.setCompoundDrawables(
                    ContextCompat.getDrawable(mContext,R.drawable.icon_error),null,null,null);
            helper.setText(R.id.tv_name, "失败原因：");
            helper.setText(R.id.tv_reason, "您的资源条件不满足，请保证良好的信用记录");
            helper.setVisible(R.id.rl_delete,true);
        }
        helper.addOnClickListener(R.id.tv_delete);

    }

//    private String code2Content(String state){
//        if("wait".equals(state)){
//
//        }
//
//    }


}
