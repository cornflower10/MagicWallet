package com.qingmang.user;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import io.reactivex.functions.Consumer;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class LoginPresenter extends BaseMvpPresenter<LoginView> {

    public void login(String phone,String passwd){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class).Login(phone,passwd)
                .compose(ResponseTransformer.<String>handleResult())
                .compose(RxSchedulers.<String>ObToMain())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String sms) throws Exception {
                            getMvpView().onDataSuccess(sms);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));

    }


}
