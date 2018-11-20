package com.kotlin.test.base.network.request

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.kotlin.test.ApplicationUtil
import com.kotlin.test.BuildConfig
import com.kotlin.test.base.Config
import com.kotlin.test.base.network.interceptor.AddCookieInterceptor
import com.kotlin.test.base.network.interceptor.SaveCookieInterceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    private var isShowLog = true


    private val httpClient: OkHttpClient by lazy {
        getOkHttpClient()
    }
    /**
     * 获取okHttpClient
     *
     * @return okHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient {
        if (null == okHttpClient) {
            okHttpClient = OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String?) {
                            if(isShowLog){
                                Log.d("xsm==",message)
                            }
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(SaveCookieInterceptor())
                    .addInterceptor(AddCookieInterceptor())
                    .build()
        }
        return this!!.okHttpClient!!
    }

    /**
     * 获取apiService
     *
     * @return apiService
     */
    fun <T> create(service: Class<T>): T {
        if (null == retrofit) {
            retrofit = Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return retrofit!!.create(service)
    }

}
