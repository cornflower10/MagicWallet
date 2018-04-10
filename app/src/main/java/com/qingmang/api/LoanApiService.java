package com.qingmang.api;

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
}
