package com.qingmang.user;

import android.text.TextUtils;

import com.qingmang.App;
import com.qingmang.api.ApiService;
import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.utils.RxSchedulers;
import com.qingmang.utils.rx.ResponseTransformer;

import java.io.File;

import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class SettingPresenter extends BaseMvpPresenter<SettingView> {

    private static final String TYPE_TEXT = "text/plain";
    private static final String TYPE_STREAM = "image/*";


    public void updateInfo(String name,
                           File file,
                           String province,
                           String city,
                           String address,
                           String workstate,
                           String income,
                           String unitname,
                           String realname,
                           String idcard,
                            String email
    ){
        MultipartBody.Part body = null;
        if(null!=file){

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("application/otcet-stream"), file);

            body  =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            LogManager.i("file"+file.length());
        }

        RequestBody nameRequest = null,
                provinceRequest= null,cityR= null,
                addressR= null,workStateR=null,incomeR=null,unitnameR =null,
                realnameR= null,idcardR= null,emailR = null;

        if(!TextUtils.isEmpty(name)){
             nameRequest =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), name);
        }

        if(!TextUtils.isEmpty(province)){
            provinceRequest =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), province);
        }
        if(!TextUtils.isEmpty(city)){
            cityR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), city);
        }

        if(!TextUtils.isEmpty(address)){
            addressR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), address);
        }

        if(!TextUtils.isEmpty(workstate)){
            workStateR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), workstate);
        }
        if(!TextUtils.isEmpty(income)){
            incomeR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), income);
        }
        if(!TextUtils.isEmpty(unitname)){
            unitnameR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), unitname);
        }

        if(!TextUtils.isEmpty(realname)){
            realnameR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), realname);
        }
        if(!TextUtils.isEmpty(idcard)){
            idcardR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), idcard);
        }
        if(!TextUtils.isEmpty(email)){
            emailR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), email);
        }
        addSubscribe(App.getInstance()
                .getRetrofitServiceManager()
                .create(ApiService.class).UpdateCustomer(
                        nameRequest,
                        body,
                        provinceRequest,
                        cityR,
                        addressR,
                        workStateR,
                        incomeR,
                        unitnameR,
                        realnameR,
                        idcardR,
                        emailR)
                .compose(ResponseTransformer.<String>handleResult())
                .compose(RxSchedulers.<String>ObToMain())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String sms) throws Exception {
                        getMvpView().onDataSuccess(sms);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onError(throwable.getMessage());
                    }
                }));

    }

}
