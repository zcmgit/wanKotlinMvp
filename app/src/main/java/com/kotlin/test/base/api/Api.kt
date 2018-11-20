package com.kotlin.test.base.api

import com.kotlin.test.base.network.BaseResponse
import com.kotlin.test.bean.HomeBannerBean
import com.kotlin.test.bean.LoginBean
import com.kotlin.test.bean.RegisterBean
import com.kotlin.test.bean.article.HomeArticleBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
interface Api {

    @POST("/user/login")
    fun login(@QueryMap map: Map<String, String>): Observable<BaseResponse<LoginBean>>

    @POST("/user/register")
    fun  register(@QueryMap map: Map<String, String>) :Observable<BaseResponse<RegisterBean>>

    @GET("/banner/json")
    fun getHomeBanner() : Observable<BaseResponse<HomeBannerBean>>

    @GET("/article/list/{pageNum}/json")
    fun getHomeArticle(@Path("pageNum") pageNum : Int) : Observable<BaseResponse<HomeArticleBean>>

}