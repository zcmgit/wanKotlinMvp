package com.kotlin.test.ui.article

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseActivity
import kotlinx.android.synthetic.main.article_activity.*
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.tool_bar.*


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

        toolbar.navigationIcon = this.resources.getDrawable(R.mipmap.back_icon)
        toolBarTitle.text = "详情"
        toolbar.setNavigationOnClickListener {
            finish()
        }

        webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })
        webview.getSettings().setJavaScriptEnabled(true)//设置webView属性，运行JS脚本
        webview.loadUrl(intent.getStringExtra("url"))//连接
    }

    override fun initLoad() {
    }


    override fun onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack()
        }else{
            super.onBackPressed()
        }
    }
}