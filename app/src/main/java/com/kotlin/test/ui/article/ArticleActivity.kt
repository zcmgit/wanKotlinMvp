package com.kotlin.test.ui.article

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseActivity
import kotlinx.android.synthetic.main.article_activity.*

/**
 * @author zcm
 * @create 2018/11/20
 * @Describe
 */
class ArticleActivity : BaseActivity() {

    private var url : String = ""
    companion object {
        fun start(context: Context, url : String){
            var intent = Intent(context,ArticleActivity::class.java)
            intent.putExtra("url",url)
            context.startActivity(intent)
        }
    }

    override fun initContentViewId(): Int {
        return R.layout.article_activity
    }

    override fun initData() {
    }

    override fun initView() {
        url = intent.getStringExtra("url")
        webview.loadUrl(url)
    }

    override fun initLoad() {
    }


}