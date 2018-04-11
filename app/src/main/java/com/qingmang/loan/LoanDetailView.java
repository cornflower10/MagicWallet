package com.qingmang.loan;

import com.qingmang.base.BaseView;

/**
 * Created by jiangpw
 * on 2018/4/11 11:56
 */
public interface LoanDetailView<T> extends BaseView {
    void onSuccess(T t);
}
