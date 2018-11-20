package com.kotlin.test.base.network.exception

/**
 * 异常统一处理类
 */
class ApiException : Exception {

    var errorCode: Int = 0
    var errorMsg: String? = null

    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.errorCode = code
    }

    constructor(code: Int, msg: String) {
        this.errorCode = code
        this.errorMsg = msg
    }

}
