package com.kotlin.test.ui.project

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.test.R
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.base.fragment.BaseMvpFragment
import com.kotlin.test.bean.article.ArticleBean
import com.kotlin.test.ui.article.ArticleActivity
import kotlinx.android.synthetic.main.project_article_item.*

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class ProjectArticleListFragment : BaseMvpFragment<ProjectArticleListPreImpl>(), ProjectArticleListContract.View{

    private var cid : Int = 0
    private var pageNum : Int = 1

    lateinit var adapter : ArticleListAdapter
    companion object {
        fun newInstance(cid : Int) =
                ProjectArticleListFragment().apply {
                   arguments = Bundle().apply {
                       putInt("cid",cid)
                   }
                }

    }

    override fun initPresenter(): ProjectArticleListPreImpl {
        return ProjectArticleListPreImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.project_article_item
    }

    override fun initData() {
        arguments?.let {
            cid = it.getInt("cid")
        }
    }

    override fun initView() {
        adapter = ArticleListAdapter(this!!.context!!,null,false)
        var manage = LinearLayoutManager(context)
        articleList.layoutManager = manage
        articleList.adapter = adapter

        adapter.setOnItemClickListener { holder, item, i ->
            ArticleActivity.start(this!!.context!!,item.link)
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            presenter.getProjectItem(pageNum,cid)
        }
    }

    override fun initLoad() {
        presenter.getProjectItem(pageNum,cid)
    }

    override fun getProjectItemSuccess(articleInfos: ArticleBean) {
        swipeRefreshLayout.isRefreshing = false
        if(adapter.dataCount == 0){
            adapter.setNewData(articleInfos.datas)
        }else{
            adapter.setLoadMoreData(articleInfos.datas)
        }
        if(pageNum == articleInfos.total){
            return
        }
        pageNum ++

    }

    override fun getProjectItemFail(msg: String) {
        swipeRefreshLayout.isRefreshing = false
    }
}