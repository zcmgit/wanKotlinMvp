package com.kotlin.test.ui.about

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseActivity
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.base.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class AboutActivity : BaseMvpActivity<AboutPresentImpl>(),AboutContract.View{
    companion object {
        fun start(context: Context){
            var intent = Intent(context,AboutActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun initPresenter(): AboutPresentImpl {
        return AboutPresentImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.activity_about
    }

    override fun initData() {
    }

    override fun initView() {
        toolbar.navigationIcon = this.resources.getDrawable(R.mipmap.back_icon)
        toolBarTitle.text = "关于"
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initLoad() {
    }

}