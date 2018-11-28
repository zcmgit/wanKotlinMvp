package com.kotlin.test.ui.login

import android.content.Context
import android.content.Intent
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.bean.LoginBean
import com.kotlin.test.ui.main.MainHomeActivity
import com.kotlin.test.ui.regist.RegistActivity
import com.kotlin.test.util.sp.SPUtil
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class LoginActivity : BaseMvpActivity<LoginPresenterImpl>(), LoginContract.View {

    companion object {
        fun start(context: Context){
            var intent = Intent(context,LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initPresenter(): LoginPresenterImpl {
        return LoginPresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.login_activity
    }

    override fun initData() {
    }

    override fun initView() {

        regist_txt.setOnClickListener {
            RegistActivity.start(this)
        }
        login_btn.setOnClickListener {
            if (name_et.text.isEmpty()){
                nameTIL.error = "用户名为空"
                nameTIL.isErrorEnabled = true
                return@setOnClickListener
            }

            if(pwd_et.text.isEmpty()){
                pwdTIL.error = "密码为空"
                pwdTIL.isErrorEnabled = true
                return@setOnClickListener
            }

            presenter.login(name_et.text.toString(),pwd_et.text.toString())
        }
    }

    override fun initLoad() {
    }

    override fun loginSuccess(t: LoginBean) {
        SPUtil.setUserName(t.username)
        t.toString()
        MainHomeActivity.start(this)
        finish()
    }

    override fun loginFail(s: String) {
        toast("登录失败"+s)
    }
}