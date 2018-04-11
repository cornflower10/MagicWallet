package com.qingmang.api;


import com.qingmang.moudle.entity.BaseEntity;
import com.qingmang.moudle.entity.CustomerInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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


    @GET("member/info")
    Observable<BaseEntity<CustomerInfo>> CusomerInfo();



    /**
     * 修改短信验证码
     * @return
     */
    @FormUrlEncoded
    @POST("member/update/sms")
    Observable<BaseEntity<String>> UpdatePasswdSms(@Field("phone") String phone
    );

    /**
     * 修改短信验证码
     * @return
     */
    @FormUrlEncoded
    @POST("member/update/pwd")
    Observable<BaseEntity<String>> UpdatePasswd(@Field("phone") String phone,
                                                   @Field("pwd") String pwd,
                                                   @Field("code") String code
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
