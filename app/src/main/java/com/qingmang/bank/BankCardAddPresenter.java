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
 * Created by xiejingbao on 2018/3/16.
 */

public class BankCardAddPresenter extends BaseMvpPresenter<CommonView> {
    private static final String TYPE_TEXT = "text/plain";
    private static final String TYPE_STREAM = "image/*";
    public void loadData(
            String bankName,
            String creditcode,
            String ownerName,
            String cvn2,
            String valiDate,
            String repayDate,
            List<File> files
    ){

//        MultipartBody.Part body = null;
//        if(null!=files&&files.size()>0){
//
//            RequestBody requestFile =
//                    RequestBody.create(MediaType.parse("application/otcet-stream"), file);
//
//            body  =
//                    MultipartBody.Part.createFormData("files", file.getName(), requestFile);
//            MultipartBody.Part.
////            LogManager.i("file"+file.length());
//        }
//
//        RequestBody bankNameR = null,
//                creditcodeR= null,ownerNameR= null,cvn2R= null,
//                valiDateR= null,repayDateR= null;
//
//        if(!TextUtils.isEmpty(bankName)){
//            bankNameR =
//                    RequestBody.create(
//                            MediaType.parse("multipart/form-data"), bankName);
//        }
//        if(!TextUtils.isEmpty(bankName)){
//            creditcodeR =
//                    RequestBody.create(
//                            MediaType.parse("multipart/form-data"), creditcode);
//        }
//        if(!TextUtils.isEmpty(bankName)){
//            ownerNameR =
//                    RequestBody.create(
//                            MediaType.parse("multipart/form-data"), ownerName);
//        }
//        if(!TextUtils.isEmpty(bankName)){
//            cvn2R =
//                    RequestBody.create(
//                            MediaType.parse("multipart/form-data"), cvn2);
//        }
//        if(!TextUtils.isEmpty(bankName)){
//            valiDateR =
//                    RequestBody.create(
//                            MediaType.parse("multipart/form-data"), valiDate);
//        }
//
//        if(!TextUtils.isEmpty(bankName)){
//            repayDateR =
//                    RequestBody.create(
//                            MediaType.parse("multipart/form-data"), repayDate);
//        }


        Map<String, RequestBody> params =new HashMap<>();
        if(!TextUtils.isEmpty(bankName))
        params.put("bankName",RequestBody.create(MediaType.parse(TYPE_TEXT),bankName));
        if(null!=files&&files.size()>0){
            for (File f:files) {
                params.put("files"+"\";fileName=\""+f.getName(),RequestBody.create(MediaType.parse(TYPE_STREAM),f));
            }
        }
        if(!TextUtils.isEmpty(creditcode))
        params.put("creditcode",RequestBody.create(MediaType.parse(TYPE_TEXT),creditcode));
        if(!TextUtils.isEmpty(ownerName))
        params.put("ownerName",RequestBody.create(MediaType.parse(TYPE_TEXT),ownerName));
        if(!TextUtils.isEmpty(cvn2))
        params.put("cvn2",RequestBody.create(MediaType.parse(TYPE_TEXT),cvn2));
        if(!TextUtils.isEmpty(valiDate))
        params.put("valiDate",RequestBody.create(MediaType.parse(TYPE_TEXT),valiDate));
        if(!TextUtils.isEmpty(repayDate))
        params.put("repayDate",RequestBody.create(MediaType.parse(TYPE_TEXT),repayDate));

        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class)
                .BdCreditCard2(params)
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
