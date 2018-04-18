package com.qingmang.bank;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.moudle.entity.BankCard;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class BankCardPresenter extends BaseMvpPresenter<BankCardView> {

    public void loadData(){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .BDCreditCards()
                .compose(ResponseTransformer.<List<BankCard>>handleResult())
                .compose(RxSchedulers.<List<BankCard>>ObToMain())
                .subscribe(new Consumer<List<BankCard>>() {
                    @Override
                    public void accept(List<BankCard> bankCards) throws Exception {
                        getMvpView().onDataSuccess(bankCards);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }


    public void loadDepositCards(){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .BDDepositCards()
                .compose(ResponseTransformer.<List<BankCard>>handleResult())
                .compose(RxSchedulers.<List<BankCard>>ObToMain())
                .subscribe(new Consumer<List<BankCard>>() {
                    @Override
                    public void accept(List<BankCard> bankCards) throws Exception {
                        getMvpView().onDataSuccess(bankCards);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }

}
