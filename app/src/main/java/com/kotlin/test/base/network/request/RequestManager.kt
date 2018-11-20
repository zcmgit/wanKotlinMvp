package com.kotlin.test.base.network.request

import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.BaseResponse
import com.kotlin.test.base.network.convert.ServerResultFunc
import com.kotlin.test.base.network.observer.BaseObserver
import com.shehuan.wanandroid.base.net.convert.ExceptionConvert
import io.reactivex.Observable


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Author: shehuan
 * Date: 2018/11/12 9:12
 * Description:
 */
object RequestManager {

    /**
     * 通用网络请求方法
     */
    fun <E> execute(presenter: BasePresenter<*>, observable: Observable<BaseResponse<E>>, observer: BaseObserver<E>): Disposable {
        observable
                .map(ServerResultFunc())
                .onErrorResumeNext(ExceptionConvert<E>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
        presenter.addDisposable(observer.disposable!!)
        return observer.disposable!!
    }



}
