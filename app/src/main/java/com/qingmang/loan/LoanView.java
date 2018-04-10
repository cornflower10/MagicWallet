package com.qingmang.loan;

import com.qingmang.base.BaseView;

/**
 * Created by jiangpw
 * on 2018/4/10 11:15
 */
public interface LoanView<T> extends BaseView {
    void onDataSuccess(T t);

    void onLoadMore(T t);
}
