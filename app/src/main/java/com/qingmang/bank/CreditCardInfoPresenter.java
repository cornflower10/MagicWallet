package com.qingmang.bank;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.moudle.entity.CreditCardInfo;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import io.reactivex.functions.Consumer;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class CreditCardInfoPresenter extends BaseMvpPresenter<CreditCardInfoView> {

    public void loadData(int id){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .HotCreidtCardInfo(id)
                .compose(ResponseTransformer.<CreditCardInfo>handleResult())
                .compose(RxSchedulers.<CreditCardInfo>ObToMain())
                .subscribe(new Consumer<CreditCardInfo>() {
                    @Override
                    public void accept(CreditCardInfo creditCardInfo) throws Exception {
                        getMvpView().onDataSuccess(creditCardInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }


}
