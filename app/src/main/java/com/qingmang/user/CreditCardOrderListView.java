package com.qingmang.user;

/**
 * Created by xiejingbao on 2018/3/16.
 */

import com.qingmang.base.BaseView;

public interface CreditCardOrderListView<D> extends BaseView {
    void onDataSuccess(D d);
    void onLoadMoreSuccess(D d);
    void onLoadRefreshSuccess(D d);
    void onLoadMoreError();
    void onDeleteSuccess();
}
