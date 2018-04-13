package com.qingmang.bank;

/**
 * Created by xiejingbao on 2018/3/16.
 */

import com.qingmang.base.BaseView;
import com.qingmang.moudle.entity.Bank;

import java.util.List;

public interface  FindView<D> extends BaseView{
    void onDataSuccess(D d);
    void onBankSuccess(List<Bank> banks);
}
