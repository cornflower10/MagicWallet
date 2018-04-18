package com.qingmang.nocardcashout;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.App;
import com.qingmang.R;
import com.qingmang.moudle.entity.BankCard;

import java.util.List;

/**
 * Created by jiangpw
 * on 2018/4/18 14:31
 */
public class NocardCashoutAdapter extends BaseQuickAdapter<BankCard, BaseViewHolder> {
    private boolean isCreditCard;

    public NocardCashoutAdapter(int layoutResId, @Nullable List<BankCard> data, boolean isCreditCard) {
        super(layoutResId, data);
        this.isCreditCard = isCreditCard;
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCard item) {
        helper.setText(R.id.tv_no_card_bank_name, item.getBankName());
        helper.setText(R.id.tv_no_card_type, isCreditCard ? "信用卡" : "储蓄卡");
        helper.setText(R.id.tv_no_card_bank_code, "( " + item.getCreditCode().substring(item.getCreditCode().length() - 4) + " )");

        switch (item.getBankName()) {
            case "中信银行":
                Glide.with(App.getInstance()).load(R.drawable.icon_card_citic).into((ImageView) helper.getView(R.id.iv_card_cashout_logo));
                break;
            case "中国农业银行":
                Glide.with(App.getInstance()).load(R.drawable.bank_card_ny).into((ImageView) helper.getView(R.id.iv_card_cashout_logo));
                break;
            case "中国银行":
                Glide.with(App.getInstance()).load(R.drawable.bank_card_zg).into((ImageView) helper.getView(R.id.iv_card_cashout_logo));
                break;
            case "中国建设银行":
                Glide.with(App.getInstance()).load(R.drawable.bank_card_jt).into((ImageView) helper.getView(R.id.iv_card_cashout_logo));
                break;
            case "招商银行":
                Glide.with(App.getInstance()).load(R.drawable.icon_card_cmb).into((ImageView) helper.getView(R.id.iv_card_cashout_logo));
                break;
            default:
                break;
        }
    }
}
