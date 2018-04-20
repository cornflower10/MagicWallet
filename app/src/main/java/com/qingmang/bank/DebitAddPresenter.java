package com.qingmang.bank;

import android.text.TextUtils;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.base.CommonView;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by jiangpw
 * on 2018/4/20 14:13
 */
public class DebitAddPresenter extends BaseMvpPresenter<CommonView> {
    private static final String TYPE_TEXT = "text/plain";
    private static final String TYPE_STREAM = "image/*";

    void apply(String bankName, String depositCode, String ownerName, String mobile, List<File> files) {
        Map<String, RequestBody> params = new HashMap<>();
        if (!TextUtils.isEmpty(bankName))
            params.put("bankName", RequestBody.create(MediaType.parse(TYPE_TEXT), bankName));
        if (null != files && files.size() > 0) {
            for (File f : files) {
                params.put("files" + "\";fileName=\"" + f.getName(), RequestBody.create(MediaType.parse(TYPE_STREAM), f));
            }
        }
        if (!TextUtils.isEmpty(depositCode))
            params.put("depositCode", RequestBody.create(MediaType.parse(TYPE_TEXT), depositCode));
        if (!TextUtils.isEmpty(ownerName))
            params.put("ownerName", RequestBody.create(MediaType.parse(TYPE_TEXT), ownerName));
        if (!TextUtils.isEmpty(mobile))
            params.put("mobile", RequestBody.create(MediaType.parse(TYPE_TEXT), mobile));

        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .bdDebitCard(params)
                .compose(ResponseTransformer.<String>handleResult())
                .compose(RxSchedulers.<String>ObToMain())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getMvpView().onDataSuccess(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));
    }
}
