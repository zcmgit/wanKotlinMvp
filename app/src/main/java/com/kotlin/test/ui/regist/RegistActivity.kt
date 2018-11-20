package com.kotlin.test.ui.regist

import android.content.Intent
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseActivity
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.bean.RegisterBean
import kotlinx.android.synthetic.main.register_activity.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class RegistActivity : BaseMvpActivity<RegistPresenterImpl>(), RegistContract.View {

    companion object {
        fun start(context : BaseActivity){
            var intent = Intent(context,RegistActivity :: class.java)
            context.startActivity(intent)
        }
    }

    override fun initPresenter(): RegistPresenterImpl {
        return RegistPresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.register_activity
    }

    override fun initData() {
    }

    override fun initView() {
        register_btn.setOnClickListener {
            presenter.regist(name_et.text.toString(), pwd_et.text.toString(), repwd_et.text.toString())
        }
    }

    override fun initLoad() {
    }

    override fun registSuccess(registerBean: RegisterBean) {
        toast("注册成功"+registerBean.username)
        finish()
    }

    override fun registFail(info : String) {
        toast("注册失败,"+info)
    }
}