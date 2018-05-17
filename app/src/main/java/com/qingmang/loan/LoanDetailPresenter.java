package com.qingmang.loan;

import com.qingmang.App;
import com.qingmang.api.LoanApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.loan.entity.LoanDetailEntity;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import io.reactivex.functions.Consumer;

/**
 * Created by jiangpw
 * on 2018/4/11 11:56
 */
public class LoanDetailPresenter extends BaseMvpPresenter<LoanDetailView> {
    /**
     * 加载数据
     *
     * @param id
     */
    void load(int id) {
        addSubscribe(App.getInstance().getRetrofitServiceManager().create(LoanApiService.class).getLoanDetail(id)
                .compose(ResponseTransformer.<LoanDetailEntity>handleResult())
                .compose(RxSchedulers.<LoanDetailEntity>ObToMain())
                .subscribe(new Consumer<LoanDetailEntity>() {
                    @Override
                    public void accept(LoanDetailEntity loanDetailEntity) throws Exception {
                        getMvpView().onSuccess(loanDetailEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }

    /**
     * 申请
     *
     * @param platId
     * @param apply
     * @param term
     */
    void apply(int platId, double apply, int term) {
        addSubscribe(App.getInstance().getRetrofitServiceManager().create(LoanApiService.class).apply(platId, apply, term)
                .compose(ResponseTransformer.<String>handleResult())
                .compose(RxSchedulers.<String>ObToMain())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getMvpView().applySuccess(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }
}
