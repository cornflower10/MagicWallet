package com.qingmang.home;

import com.qingmang.base.BaseView;

/**
 * Created by jiangpw
 * on 2018/4/13 14:55
 */
public interface HomeView<T> extends BaseView {
    void bannerSuccess(T t);

    void loanSuccess(T t);

    void messageSuccess();
}
