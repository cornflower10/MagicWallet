package com.qingmang.bank;

/**
 * Created by xiejingbao on 2018/3/16.
 */

import com.qingmang.base.BaseView;
import com.qingmang.moudle.entity.Bank;

import java.util.List;

public interface CreditCardInfoView<D> extends BaseView{
    void onDataSuccess(D d);
    void onApplySuccess(List<Bank> banks);
}
