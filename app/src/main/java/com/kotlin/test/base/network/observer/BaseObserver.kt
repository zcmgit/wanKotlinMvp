package com.kotlin.test.base.network.observer

import com.kotlin.test.base.network.exception.ApiException
import com.kotlin.test.base.network.exception.ExceptionEngine

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T> : Observer<T> {
    private var showErrorToast = true

    var disposable: Disposable? = null
        private set

    constructor() {}

    constructor(showErrorToast: Boolean) {
        this.showErrorToast = showErrorToast
    }

    override fun onSubscribe(d: Disposable) {
        this.disposable = d
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        val errorMsg: String
        if (e is ApiException) {
            val exception = e as ApiException
            errorMsg = exception.errorMsg!!
            onFail(exception.errorCode, exception.errorMsg!!)
        } else {
            errorMsg = "未知错误"
            onFail(ExceptionEngine.ERROR.UNKNOWN, errorMsg)
        }

        if (showErrorToast) {
//            Toast(errorMsg)
        }
    }

    override fun onComplete() {}

    abstract fun onSuccess(t: T)

    abstract fun onFail(code: Int, msg: String)
}
