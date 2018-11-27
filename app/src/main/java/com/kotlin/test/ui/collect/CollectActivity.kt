package com.kotlin.test.ui.collect

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.base.adapter.SystemAdapter
import com.kotlin.test.bean.CollectBean
import com.kotlin.test.bean.article.ArticleBean
import com.kotlin.test.ui.article.ArticleActivity
import kotlinx.android.synthetic.main.fragment_system.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class CollectActivity : BaseMvpActivity<CollectPresentImpl>(), CollectContract.View {

    lateinit var articleListAdapter: ArticleListAdapter
    private var pageNum: Int = 0

    companion object {
        fun start(context: Context) {
            var intent = Intent(context, CollectActivity::class.java)
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

        articleListAdapter = ArticleListAdapter(this!!.context!!, null, false)
        var manage = LinearLayoutManager(context)
        articleList.layoutManager = manage
        articleList.adapter = articleListAdapter

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            presenter.getCollectInfos(pageNum)
        }
        articleListAdapter.setOnItemClickListener { viewHolder, dataItem, i ->
            ArticleActivity.start(this, dataItem.link)
        }
    }

    override fun initLoad() {
        presenter.getCollectInfos(pageNum)
    }

    override fun getCollectSuccess(collectInfo: ArticleBean) {
        if (articleListAdapter.itemCount == 0) {
            articleListAdapter.setNewData(collectInfo.datas)
        } else {
            articleListAdapter.setLoadMoreData(collectInfo.datas)
        }
        if (pageNum == collectInfo.total) {
            return
        }
        pageNum++
    }

    override fun getCollectFail(msg: String) {
        toast(msg)
    }
}