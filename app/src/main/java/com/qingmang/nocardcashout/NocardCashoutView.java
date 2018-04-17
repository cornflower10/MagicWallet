package com.qingmang.nocardcashout;

import com.qingmang.base.BaseView;
import com.qingmang.moudle.entity.BankCard;

import java.util.List;

/**
 * Created by jiangpw
 * on 2018/4/17 15:30
 */
public interface NocardCashoutView extends BaseView {
    void onSuccessDebitCard(List<BankCard> debitCards);

    void onSuccessCreditCard(List<BankCard> creditCards);

    void applySuccess(String msg);
}
