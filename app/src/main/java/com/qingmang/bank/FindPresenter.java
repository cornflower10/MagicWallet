package com.qingmang.bank;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.moudle.entity.Bank;
import com.qingmang.moudle.entity.CreditCard;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class FindPresenter extends BaseMvpPresenter<FindView> {

    public void loadData(){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .HotCreidtCard(5)
                .compose(ResponseTransformer.<List<CreditCard>>handleResult())
                .compose(RxSchedulers.<List<CreditCard>>ObToMain())
                .subscribe(new Consumer<List<CreditCard>>() {
                    @Override
                    public void accept(List<CreditCard> creditCards) throws Exception {
                        getMvpView().onDataSuccess(creditCards);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }


    public void hotBanks(){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .HotBank(4)
                .compose(ResponseTransformer.<List<Bank>>handleResult())
                .compose(RxSchedulers.<List<Bank>>ObToMain())
                .subscribe(new Consumer<List<Bank>>() {
                    @Override
                    public void accept(List<Bank> banks) throws Exception {
                        getMvpView().onBankSuccess(banks);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }
}
