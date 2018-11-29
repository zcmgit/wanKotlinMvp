package com.kotlin.test.ui.tixi

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.bean.article.ArticleBean
import com.kotlin.test.ui.article.ArticleActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class SystemArticleListActivity : BaseMvpActivity<SystemArticlePresenter>(), SystemArticleContract.View {
    private var cid: Int = 0
    private var title: String = ""
    private var pageNum: Int = 0

    companion object {
        fun start(context: Context, cid: Int, title: String) {
            var intent = Intent(context, SystemArticleListActivity::class.java)
            intent.putExtra("cid", cid)
            intent.putExtra("title", title)
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
        cid = intent.getIntExtra("cid", 0)
        title = intent.getStringExtra("title")
    }

    override fun initView() {
        toolbar.navigationIcon = this.resources.getDrawable(R.mipmap.back_icon)
        toolBarTitle.text = title
        toolbar.setNavigationOnClickListener {
            finish()
        }

        articleListAdapter = ArticleListAdapter(this, null, true).apply {
            setOnLoadMoreListener {
                presenter.getSystemArticleInfo(pageNum, cid)
            }
            setOnItemClickListener { viewHolder, dataItem, i ->
                ArticleActivity.start(context, dataItem.link)
            }
            setOnLoadMoreListener {
                presenter.getSystemArticleInfo(pageNum, cid)
            }
        }
        articleList?.layoutManager = LinearLayoutManager(this)
        articleList?.adapter = articleListAdapter

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            pageNum = 0
            presenter.getSystemArticleInfo(pageNum, cid)
        }
    }

    override fun initLoad() {
        presenter.getSystemArticleInfo(pageNum, cid)
    }

    override fun getSystemArticleSuccess(bean: ArticleBean) {
        swipeRefreshLayout.isRefreshing = false
        if (pageNum == 0) {
            articleListAdapter.setNewData(bean.datas)
        } else {
            articleListAdapter.setLoadMoreData(bean.datas)
        }
        if (pageNum + 1 == bean.pageCount) {
            return
        }
        pageNum++
    }

    override fun getSystemArticleFail(msg: String) {
        swipeRefreshLayout.isRefreshing = false
    }
}