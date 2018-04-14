package com.qingmang.bank;

/**
 * Created by xiejingbao on 2018/3/16.
 */

import com.qingmang.base.BaseView;

public interface BankCardView<D> extends BaseView{
    void onDataSuccess(D d);
}
