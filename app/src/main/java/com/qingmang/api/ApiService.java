package com.qingmang.api;


import com.qingmang.moudle.entity.Bank;
import com.qingmang.moudle.entity.BankCard;
import com.qingmang.moudle.entity.BankInfo;
import com.qingmang.moudle.entity.BaseEntity;
import com.qingmang.moudle.entity.CreditCard;
import com.qingmang.moudle.entity.CreditCardInfo;
import com.qingmang.moudle.entity.CustomerInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by xiejingbao on 2017/5/18.
 */

public interface ApiService {

    /**
     * 注册
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
     *
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
     *
     * @return
     */
    @FormUrlEncoded
    @POST("member/update/sms")
    Observable<BaseEntity<String>> UpdatePasswdSms(@Field("phone") String phone
    );

    /**
     * 修改短信验证码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("member/update/pwd")
    Observable<BaseEntity<String>> UpdatePasswd(@Field("phone") String phone,
                                                @Field("pwd") String pwd,
                                                @Field("code") String code
    );



    @Multipart
    @POST("member/info/update")
    Observable<BaseEntity<String>> UpdateCustomer(@Part("name") RequestBody name,
                                                   @Part MultipartBody.Part file,
                                                   @Part("province") RequestBody province,
                                                   @Part("city") RequestBody city,
                                                   @Part("address") RequestBody address,

                                                   @Part("workstate") RequestBody workstate,
                                                   @Part("income") RequestBody income,
                                                   @Part("unitname") RequestBody unitname,

                                                   @Part("realname") RequestBody realname,
                                                   @Part("idcard") RequestBody idcard,
                                                   @Part("email") RequestBody email);



    /**
     * 热门信用卡
     *
     * @return
     */
    @FormUrlEncoded
    @POST("open/card/hot")
    Observable<BaseEntity<List<CreditCard>>> HotCreidtCard(@Field("pageSize") int pageSize
    );


    /**
     * 热门信用卡详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("open/card/info")
    Observable<BaseEntity<CreditCardInfo>> HotCreidtCardInfo(@Field("id") long id
    );

    /**
     * 申请信用卡
     *
     * @return
     */
    @FormUrlEncoded
    @POST("member/apply/card")
    Observable<BaseEntity<String>> ApplyCreidtCard(@Field("cardId") long id
    );


    /**
     * 热门银行
     *
     * @return
     */
    @FormUrlEncoded
    @POST("open/bank/hot")
    Observable<BaseEntity<List<Bank>>> HotBank(@Field("pageSize") int pageSize);

    /**
     * 热门银行详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("open/bank/info")
    Observable<BaseEntity<BankInfo>> HotBankInfo(@Field("id") long id);



    @GET("member/bind/credit/list")
    Observable<BaseEntity<List<BankCard>>> BDCreditCards();


    @GET("member/bind/deposit/list")
    Observable<BaseEntity<List<BankCard>>> BDDepositCards();


    @Multipart
    @POST("member/bind/credit")
    Observable<BaseEntity<String>> BdCreditCard2(@PartMap Map<String, RequestBody> params);


    @Multipart
    @POST("member/bind/credit")
    Observable<BaseEntity<String>> BdCreditCard(@Part("bankName") RequestBody bankName,
                                                @Part("creditcode") RequestBody creditcode,
                                                @Part("ownerName") RequestBody ownerName,
//                                                @Part("creditLine") RequestBody creditLine,
                                                @Part("cvn2") RequestBody cvn2,
                                                @Part("valiDate") RequestBody valiDate,
                                                @Part("repayDate") RequestBody repayDate,
                                                @Part MultipartBody.Part file
    );



}
