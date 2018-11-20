package com.kotlin.test.base.network.observer

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.kotlin.test.dialog.LoadingDialog

import com.shehuan.nicedialog.BaseNiceDialog

import java.lang.ref.WeakReference

import io.reactivex.disposables.Disposable

/**
 * Author: shehuan
 * Date: 2018/9/21 11:00
 * Description:
 */
abstract class LoadingObserver<T> : BaseObserver<T> {
    private var loadingDialog: BaseNiceDialog? = null
    private var wrContext: WeakReference<Context>? = null

    /**
     * 显示loading的构造函数
     */
    constructor(context: Context, showLoading: Boolean) {
        if (showLoading) {
            wrContext = WeakReference(context)
            loadingDialog = LoadingDialog.newInstance()
        }
    }

    constructor(context: Context, showLoading: Boolean, showErrorToast: Boolean) : super(showErrorToast) {
        if (showLoading) {
            wrContext = WeakReference(context)
            loadingDialog = LoadingDialog.newInstance()
        }
    }

    override fun onSubscribe(d: Disposable) {
        showDialog()
        super.onSubscribe(d)
    }

    override fun onNext(o: T) {
        dismissDialog()
        super.onNext(o)
    }

    override fun onError(e: Throwable) {
        dismissDialog()
        super.onError(e)
    }

    private fun showDialog() {
        if (loadingDialog != null) {
            loadingDialog!!.show((wrContext!!.get() as AppCompatActivity).supportFragmentManager)
        }
    }

    private fun dismissDialog() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
            loadingDialog = null
            wrContext!!.clear()
        }
    }
}
