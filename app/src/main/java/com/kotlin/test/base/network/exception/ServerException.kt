package com.kotlin.test.base.network.exception

/**
 * 自定义服务器错误
 */
class ServerException(val code: Int, val msg: String) : RuntimeException()
