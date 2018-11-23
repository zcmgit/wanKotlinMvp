package com.kotlin.test.ui.tixi

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.bean.article.HomeArticleBean
import com.kotlin.test.ui.article.ArticleActivity
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.internal.Internal

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class SystemArticleListActivity : BaseMvpActivity<SystemArticlePresenter>(), SystemArticleContract.View {
    private var cid: Int = 0
    private var pageNum : Int = 0
    companion object {
        fun start(context: Context, cid: Int){
            var intent = Intent(context,SystemArticleListActivity::class.java)
            intent.putExtra("cid",cid)
            context.startActivity(intent)
        }
    }

    private lateinit var articleListAdapter: ArticleListAdapter

    override fun initPresenter(): SystemArticlePresenter {
        return SystemArticlePresenter(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.system_article_activity
    }

    override fun initData() {
        cid = intent.getIntExtra("cid",0)
    }

    override fun initView() {
        articleListAdapter = ArticleListAdapter(this, null, true)
        articleList?.layoutManager = LinearLayoutManager(this)
        articleList?.adapter = articleListAdapter
        articleListAdapter.setOnItemClickListener { viewHolder, dataItem, i ->
            ArticleActivity.start(this,dataItem.link)
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            presenter.getSystemArticleInfo(0,cid)
        }
        articleListAdapter.setOnLoadMoreListener {
            presenter.getSystemArticleInfo(pageNum,cid)
        }
    }

    override fun initLoad() {
        presenter.getSystemArticleInfo(pageNum,cid)
    }

    override fun getSystemArticleSuccess(bean: HomeArticleBean) {
        swipeRefreshLayout.isRefreshing = false
        if(articleListAdapter.dataCount == 0){
            articleListAdapter.setNewData(bean.datas)
        }else{
            articleListAdapter.setLoadMoreData(bean.datas)
        }
        if(pageNum == bean.total){
            return
        }
        pageNum ++
    }

    override fun getSystemArticleFail(msg: String) {
        swipeRefreshLayout.isRefreshing = false
    }
}