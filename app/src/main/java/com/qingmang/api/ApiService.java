package com.qingmang.api;


import com.qingmang.moudle.entity.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by xiejingbao on 2017/5/18.
 */

public interface ApiService {

    /**
     * 注册
     *
     */
    @FormUrlEncoded
    @POST("member/regist")
    Observable<BaseEntity<String>> Regist(@Field("phone") String phone,
                                 @Field("code") String code,
                                  @Field("phoneModel") String phoneModel,
                                  @Field("pwd") String pwd);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("member/login")
    Observable<BaseEntity<String>> Login(@Field("phone") String phone,
                                              @Field("pwd") String passWd
                                              );

    /**
     * 注册短信验证码
     * @return
     */
    @FormUrlEncoded
    @POST("regist/sms")
    Observable<BaseEntity<String>> RegistSMS(@Field("phone") String phone,
                                                     @Field("idCard") String idCard
    );


//
//
//
//    @FormUrlEncoded
//    @POST("agency/shuanglu/changePassword")
//    Observable<ChangePasswdEntity> changePasswd(@Field("oldPassword") String userName,
//                                                @Field("newPassword") String passWd,
//                                                @Field("sign") String sign
//    );
//
//    //上传同时
//    @Multipart
//    @POST("agency/shuanglu/submitIntentAgencyInfo")
//    Observable<UploadEntity> uploads(@PartMap Map<String, RequestBody> params
//    );
//
//    @FormUrlEncoded
//    @POST("agency/shuanglu/isAgencyNameUsed")
//    Observable<AgencyNameUsed> isAgencyNameUsed(@Field("customName") String customName,
//                                                @Field("sign") String sign);
}
