package com.kotlin.test.base.network.interceptor

import com.kotlin.test.util.SPUtil

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 用于添加头部固定参数和cookie的拦截器
 */

class AddCookieInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val cookieSet = SPUtil.getCookies()
        val builder = chain.request().newBuilder()
        var cookies = SPUtil.getCookies().toString().split("*")
        //添加cookie，到请求头中
        if (!cookies.isEmpty()) {
            for (cookie in cookies) {
                builder.addHeader("Cookie", cookie)
            }
        }

        return chain.proceed(builder.build())
    }
}