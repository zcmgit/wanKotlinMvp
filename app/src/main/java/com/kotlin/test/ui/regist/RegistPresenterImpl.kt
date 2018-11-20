package com.kotlin.test.ui.regist

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.RegisterBean
import com.kotlin.test.ui.login.LoginContract

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class RegistPresenterImpl (view: RegistContract.View) : BasePresenter<RegistContract.View>(view), RegistContract.Persenter{
    override fun regist(name: String, pwd: String, repwd: String) {
        var registMap = hashMapOf<String ,String>()
        registMap["username"] = name
        registMap["password"] = pwd
        registMap["repassword"] = repwd
        RequestManager.execute(this,RetrofitManager.create(Api :: class.java).register(registMap),
                object : BaseObserver<RegisterBean>(){
                    override fun onSuccess(t: RegisterBean) {
                        view.registSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.registFail(msg)
                    }

                })
    }
}