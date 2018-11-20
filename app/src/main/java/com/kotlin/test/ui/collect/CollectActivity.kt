package com.kotlin.test.ui.collect

import android.content.Context
import android.content.Intent
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class CollectActivity : BaseMvpActivity<CollectPresentImpl>(),CollectContract.View{

    companion object {
        fun start(context: Context){
            var intent = Intent(context,CollectActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initPresenter(): CollectPresentImpl {
        return CollectPresentImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.activity_collect
    }

    override fun initData() {
    }

    override fun initView() {
        toolbar.navigationIcon = this.resources.getDrawable(R.mipmap.back_icon)
        toolBarTitle.text = "收藏"
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initLoad() {
    }

}