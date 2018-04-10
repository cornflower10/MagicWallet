package com.qingmang.user;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.moudle.entity.CustomerInfo;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import io.reactivex.functions.Consumer;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class MyPresenter extends BaseMvpPresenter<MyView> {

    public void loadData(){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .CusomerInfo()
                .compose(ResponseTransformer.<CustomerInfo>handleResult())
                .compose(RxSchedulers.<CustomerInfo>ObToMain())
                .subscribe(new Consumer<CustomerInfo>() {
                    @Override
                    public void accept(CustomerInfo customerInfo) throws Exception {
                            getMvpView().onDataSuccess(customerInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }

}
