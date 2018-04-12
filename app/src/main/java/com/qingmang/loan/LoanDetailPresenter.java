package com.qingmang.loan;

import com.qingmang.App;
import com.qingmang.R;
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
                        getMvpView().onError(App.getInstance().getResources().getString(R.string.fail_message));
                    }
                }));
    }
}
