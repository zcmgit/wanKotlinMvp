package com.kotlin.test.base.api

import com.kotlin.test.base.network.BaseResponse
import com.kotlin.test.bean.HomeBannerBean
import com.kotlin.test.bean.LoginBean
import com.kotlin.test.bean.RegisterBean
import com.kotlin.test.bean.article.HomeArticleBean
import com.kotlin.test.bean.system.SystemInfoBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
interface Api {

    @POST("/user/login")
    fun login(@QueryMap map: Map<String, String>): Observable<BaseResponse<LoginBean>>

    @POST("/user/register")
    fun register(@QueryMap map: Map<String, String>): Observable<BaseResponse<RegisterBean>>

    @GET("/banner/json")
    fun getHomeBanner(): Observable<BaseResponse<List<HomeBannerBean>>>

    @GET("/article/list/{pageNum}/json")
    fun getHomeArticle(@Path("pageNum") pageNum: Int): Observable<BaseResponse<HomeArticleBean>>

    @GET("/tree/json")
    fun getSystemInfo(): Observable<BaseResponse<List<SystemInfoBean>>>

    @GET("/article/list/{pageNum}/json?")
    fun getSystemArticle(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int) : Observable<BaseResponse<HomeArticleBean>>
}