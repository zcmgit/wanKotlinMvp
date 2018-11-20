package com.kotlin.test.base.network.exception

import com.google.gson.JsonParseException

import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

import javax.net.ssl.SSLHandshakeException

import retrofit2.HttpException

class ExceptionEngine {

    /**
     * 约定异常
     */
    object ERROR {
        /**
         * 未知错误
         */
        val UNKNOWN = 1000
        /**
         * 解析错误
         */
        val PARSE_ERROR = 1001
        /**
         * 网络错误
         */
        val NETWORD_ERROR = 1002
        /**
         * 协议出错
         */
        val HTTP_ERROR = 1003

        /**
         * 证书出错
         */
        val SSL_ERROR = 1005

        /**
         * 连接超时
         */
        val TIMEOUT_ERROR = 1006
    }

    companion object {

        private val UNAUTHORIZED = 401
        private val FORBIDDEN = 403
        private val NOT_FOUND = 404
        private val REQUEST_TIMEOUT = 408
        private val INTERNAL_SERVER_ERROR = 500
        private val BAD_GATEWAY = 502
        private val SERVICE_UNAVAILABLE = 503
        private val GATEWAY_TIMEOUT = 504

        fun handleException(e: Throwable): ApiException {
            val ex: ApiException
            if (e is HttpException) {//http错误
                val httpExc = e as HttpException
                ex = ApiException(e, ERROR.HTTP_ERROR)
                when (httpExc.code()) {
                    UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.errorMsg = "网络错误"
                    else -> ex.errorMsg = "网络错误"
                }
                return ex
            } else if (e is ServerException) {//服务器返回的错误
                ex = ApiException(e, e.code)
                ex.errorMsg = e.msg
                return ex
            } else if (e is JsonParseException
                    || e is JSONException
                    || e is ParseException) {//解析数据错误
                ex = ApiException(e, ERROR.PARSE_ERROR)
                ex.errorMsg = "解析错误"
                return ex
            } else if (e is ConnectException) {//连接网络错误
                ex = ApiException(e, ERROR.NETWORD_ERROR)
                ex.errorMsg = "连接失败"
                return ex
            } else if (e is SSLHandshakeException) {//连接网络错误
                ex = ApiException(e, ERROR.SSL_ERROR)
                ex.errorMsg = "证书验证失败"
                return ex
            } else if (e is ConnectTimeoutException) {//网络超时
                ex = ApiException(e, ERROR.TIMEOUT_ERROR)
                ex.errorMsg = "连接超时"
                return ex
            } else if (e is SocketTimeoutException) {//网络超时
                ex = ApiException(e, ERROR.TIMEOUT_ERROR)
                ex.errorMsg = "连接超时"
                return ex
            } else {//未知错误
                ex = ApiException(e, ERROR.UNKNOWN)
                ex.errorMsg = "未知错误"
                return ex
            }
        }
    }

}
