package com.kotlin.test.base.api

import com.kotlin.test.base.network.BaseResponse
import com.kotlin.test.bean.*
import com.kotlin.test.bean.article.ArticleBean
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
    fun getHomeArticle(@Path("pageNum") pageNum: Int): Observable<BaseResponse<ArticleBean>>

    @GET("/tree/json")
    fun getSystemInfo(): Observable<BaseResponse<List<SystemInfoBean>>>

    @GET("/article/list/{pageNum}/json?")
    fun getSystemArticle(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticleBean>>

    @GET("/project/tree/json")
    fun getProjectInfo(): Observable<BaseResponse<List<ProjectBean>>>

    @GET("/project/list/{pageNum}/json?")
    fun getProjectListInfo(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticleBean>>

    @GET("/lg/collect/list/{pageNum}/json")
    fun getCollectInfos(@Path("pageNum") pageNum: Int): Observable<BaseResponse<ArticleBean>>

    @POST("/lg/collect/{id}/json")
    fun setCollect(@Path("id") id : Int): Observable<BaseResponse<String>>

    @POST("/lg/uncollect_originId/{id}/json")
    fun setUnCollect(@Path("id") id: Int): Observable<BaseResponse<String>>

    @GET("/hotkey/json")
    fun getHotInfos(): Observable<BaseResponse<List<HotBean>>>

    @GET("/friend/json")
    fun getFriendWebInfos(): Observable<BaseResponse<List<WebBean>>>

    @POST("/article/query/{pageNum}/json")
    fun searchInfoByKey(@Path("pageNum") pageNum: Int, @Query("k") key: String) : Observable<BaseResponse<ArticleBean>>

}