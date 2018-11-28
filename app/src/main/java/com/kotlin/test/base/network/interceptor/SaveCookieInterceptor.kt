package com.kotlin.test.base.network.interceptor

import com.kotlin.test.util.sp.SPUtil

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 获取cookie 拦截器，能够获取到登录过后的cookie值
 */

class SaveCookieInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(chain.request())

        if (request.url().toString().contains("login")) {
            val cookies = response.headers("Set-Cookie")
            var cookieStr = StringBuffer()
            if (!cookies.isEmpty()) {
                for (cookie in cookies){
                    cookieStr.append(cookie)
                    cookieStr.append("*")
                }
                SPUtil.saveCookie(cookieStr.toString())
            }
        }
        return response
    }
}
