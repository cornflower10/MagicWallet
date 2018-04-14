package com.qingmang.bank;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.base.CommonView;
import com.qingmang.moudle.entity.BankInfo;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import io.reactivex.functions.Consumer;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class HotBankkInfoPresenter extends BaseMvpPresenter<CommonView> {

    public void loadData(long id){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .HotBankInfo(id)
                .compose(ResponseTransformer.<BankInfo>handleResult())
                .compose(RxSchedulers.<BankInfo>ObToMain())
                .subscribe(new Consumer<BankInfo>() {
                    @Override
                    public void accept(BankInfo bankInfo) throws Exception {
                        getMvpView().onDataSuccess(bankInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }


}
