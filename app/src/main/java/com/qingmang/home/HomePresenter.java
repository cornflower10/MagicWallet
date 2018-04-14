package com.qingmang.home;

import com.qingmang.App;
import com.qingmang.R;
import com.qingmang.api.ApiService;
import com.qingmang.api.LoanApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.loan.entity.LoanListEntity;
import com.qingmang.moudle.entity.Bank;
import com.qingmang.moudle.entity.CreditCard;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by jiangpw
 * on 2018/4/13 14:54
 */
public class HomePresenter extends BaseMvpPresenter<HomeView> {
    void loadBanner() {

    }

    void loadLoan(int pageNumber, int pageSize, String orderCond, final boolean isLoadMore) {
        addSubscribe(App.getInstance().getRetrofitServiceManager().create(LoanApiService.class).getLoanList(pageNumber, pageSize, orderCond)
                .compose(ResponseTransformer.<LoanListEntity>handleResult())
                .compose(RxSchedulers.<LoanListEntity>ObToMain())
                .subscribe(new Consumer<LoanListEntity>() {
                    @Override
                    public void accept(LoanListEntity loanListEntity) throws Exception {
                        getMvpView().loanSuccess(loanListEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }

    void loadMessage() {

    }

    public void hotBanks(){
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .HotCreidtCard(5)
                .compose(ResponseTransformer.<List<CreditCard>>handleResult())
                .compose(RxSchedulers.<List<CreditCard>>ObToMain())
                .subscribe(new Consumer<List<CreditCard>>() {
                    @Override
                    public void accept(List<CreditCard> creditCards) throws Exception {
                        getMvpView().onCardSuccess(creditCards);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }
}
