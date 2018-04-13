package com.qingmang.api;

import com.qingmang.loan.entity.LoanDetailEntity;
import com.qingmang.loan.entity.LoanListEntity;
import com.qingmang.moudle.entity.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jiangpw
 * on 2018/4/10 11:58
 */
public interface LoanApiService {
    /**
     * 列表
     *
     * @param pageNumber
     * @param pageSize
     * @param orderCond
     * @return
     */
    @FormUrlEncoded
    @POST("open/plat/hot/page")
    Observable<BaseEntity<LoanListEntity>> getLoanList(@Field("pageNumber") int pageNumber,
                                                       @Field("pageSize") int pageSize,
                                                       @Field("orderCond") String orderCond);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("open/plat/info")
    Observable<BaseEntity<LoanDetailEntity>> getLoanDetail(@Field("id") int id);


    /**
     * 申请
     *
     * @param platId
     * @param apply
     * @param term
     * @return
     */
    @FormUrlEncoded
    @POST("member/apply/plat")
    Observable<BaseEntity<String>> apply(@Field("platId") long platId, @Field("apply") double apply, @Field("term") int term);
}
