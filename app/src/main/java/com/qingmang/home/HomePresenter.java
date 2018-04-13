package com.qingmang.home;

import com.qingmang.App;
import com.qingmang.R;
import com.qingmang.api.LoanApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.loan.entity.LoanListEntity;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

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
                        getMvpView().onError(App.getInstance().getResources().getString(R.string.fail_message));
                    }
                }));
    }

    void loadMessage() {

    }
}
