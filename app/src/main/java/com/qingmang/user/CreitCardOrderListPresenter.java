package com.qingmang.user;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.moudle.entity.Loan;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import io.reactivex.functions.Consumer;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class CreitCardOrderListPresenter extends BaseMvpPresenter<CreditCardOrderListView> {

    public void creditCardOrderList(int page, final boolean isRefresh){

        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class).LoanOrderList(page,10)
                .compose(ResponseTransformer.<Loan>handleResult())
                .compose(RxSchedulers.<Loan>ObToMain())
                .subscribe(new Consumer<Loan>() {
                    @Override
                    public void accept(Loan loan) throws Exception {
                        if(isRefresh)
                            getMvpView().onLoadRefreshSuccess(loan);
                        else
                            getMvpView().onDataSuccess(loan);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));

    }


    public void creditCardOrderListLoadMore(int page){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class).LoanOrderList(page,10)
                .compose(ResponseTransformer.<Loan>handleResult())
                .compose(RxSchedulers.<Loan>ObToMain())
                .subscribe(new Consumer<Loan>() {
                    @Override
                    public void accept(Loan loan) throws Exception {
                        getMvpView().onLoadMoreSuccess(loan);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                        getMvpView().onLoadMoreError();
                    }
                }));

    }


    public void deleteCreditCardOrder(int id){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class).DeleteLoanOrder(Long.valueOf(id))
                .compose(ResponseTransformer.<String>handleResult())
                .compose(RxSchedulers.<String>ObToMain())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getMvpView().onDeleteSuccess();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));

    }
}
