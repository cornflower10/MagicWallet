package com.qingmang.loan;

import android.util.Log;

import com.qingmang.App;
import com.qingmang.api.LoanApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.loan.entity.LoanListEntity;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import io.reactivex.functions.Consumer;

/**
 * Created by jiangpw
 * on 2018/4/10 11:15
 */
public class LoanPresenter extends BaseMvpPresenter<LoanView> {
    void loadData(int pageNumber, int pageSize, String orderCond) {
        addSubscribe(App.getInstance().getRetrofitServiceManager().create(LoanApiService.class).getLoanList(pageNumber, pageSize, orderCond)
                .compose(ResponseTransformer.<LoanListEntity>handleResult())
                .compose(RxSchedulers.<LoanListEntity>ObToMain())
                .subscribe(new Consumer<LoanListEntity>() {
                    @Override
                    public void accept(LoanListEntity loanListEntity) throws Exception {
                        Log.e("GG", "GG");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("GG", "GG");
                    }
                }));
    }
}
