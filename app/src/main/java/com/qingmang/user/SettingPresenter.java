package com.qingmang.user;

import android.text.TextUtils;

import com.qingmang.base.BaseMvpPresenter;
import com.qingmang.baselibrary.utils.LogManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by xiejingbao on 2018/3/16.
 */

public class SettingPresenter extends BaseMvpPresenter<SettingView> {

    private static final String TYPE_TEXT = "text/plain";
    private static final String TYPE_STREAM = "image/*";

//    public void updateInfo(String name,
//                           File file,
//                           String province,
//                           String city,
//                           String district,
//                           String address,
//                           String realname,
//                           String idcard
//                           ){
//        LogManager.i("file"+file.length());
//        Map<String, RequestBody> params =new HashMap<>();
//        if(!TextUtils.isEmpty(name))
//        params.put("name",RequestBody.create(MediaType.parse(TYPE_TEXT),name));
//        if(null!=file)
//        params.put("file"+"\";fileName=\""+file.getName(),RequestBody.create(MediaType.parse(TYPE_STREAM),file));
//        if(!TextUtils.isEmpty(province))
//        params.put("province",RequestBody.create(MediaType.parse(TYPE_TEXT),province));
//        if(!TextUtils.isEmpty(city))
//        params.put("city",RequestBody.create(MediaType.parse(TYPE_TEXT),city));
//        if(!TextUtils.isEmpty(district))
//        params.put("district",RequestBody.create(MediaType.parse(TYPE_TEXT),district));
//        if(!TextUtils.isEmpty(address))
//        params.put("address",RequestBody.create(MediaType.parse(TYPE_TEXT),address));
//        if(!TextUtils.isEmpty(realname))
//        params.put("realname",RequestBody.create(MediaType.parse(TYPE_TEXT),realname));
//        if(!TextUtils.isEmpty(idcard))
//        params.put("idcard",RequestBody.create(MediaType.parse(TYPE_TEXT),idcard));
//        addSubscribe(App.getInstance()
//                .getRetrofitServiceManager()
//                .create(ApiService.class).UpdateCustomer(params)
//                .compose(ResponseTransformer.<String>handleResult())
//                .compose(RxSchedulers.<String>ObToMain())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String sms) throws Exception {
//                            getMvpView().onDataSuccess(sms);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        getMvpView().onError(throwable.getMessage());
//                    }
//                }));
//
//    }

    public void updateInfo2(String name,
                           File file,
                           String province,
                           String city,
                           String district,
                           String address,
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
                provinceRequest= null,cityR= null,districtR= null,
                addressR= null,realnameR= null,idcardR= null,emailR = null;

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
        if(!TextUtils.isEmpty(district)){
            districtR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), district);
        }
        if(!TextUtils.isEmpty(address)){
            addressR =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), address);
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
//        addSubscribe(App.getInstance()
//                .getRetrofitServiceManager()
//                .create(ApiService.class).UpdateCustomer2(nameRequest,
//                        body,
//                        provinceRequest,
//                        cityR,
//                        districtR,
//                        addressR,
//                        realnameR,
//                        idcardR,
//                        emailR)
//                .compose(ResponseTransformer.<String>handleResult())
//                .compose(RxSchedulers.<String>ObToMain())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String sms) throws Exception {
//                        getMvpView().onDataSuccess(sms);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        getMvpView().onError(throwable.getMessage());
//                    }
//                }));

    }

}
