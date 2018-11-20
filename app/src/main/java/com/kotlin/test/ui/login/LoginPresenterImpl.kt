package com.kotlin.test.ui.login

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.LoginBean

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class LoginPresenterImpl(view: LoginContract.View) : BasePresenter<LoginContract.View>(view), LoginContract.Presenter {
    override fun login(name: String, pwd: String) {
        var loginMap = hashMapOf<String,String>()
        loginMap["username"] = name
        loginMap["password"] = pwd
        RequestManager.execute(this,RetrofitManager.create(Api :: class.java).login(loginMap),
                object : BaseObserver<LoginBean>() {
                    override fun onSuccess(t: LoginBean) {
                        view.loginSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.loginFail(msg)
                    }

                })
    }

}