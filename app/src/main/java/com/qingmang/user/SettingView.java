package com.qingmang.user;

/**
 * Created by xiejingbao on 2018/3/16.
 */

import com.qingmang.base.BaseView;

public interface SettingView<D> extends BaseView {
    void onDataSuccess(D d);
}
