package com.kotlin.test.ui.project

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kotlin.test.R
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.base.fragment.BaseMvpFragment
import com.kotlin.test.bean.article.ArticleBean
import com.kotlin.test.ui.article.ArticleActivity
import kotlinx.android.synthetic.main.project_article_item.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class ProjectArticleListFragment : BaseMvpFragment<ProjectArticleListPreImpl>(), ProjectArticleListContract.View {

    private var cid: Int = 0
    private var pageNum: Int = 1

    private var pageAllNum: Int = -1
    private var listNum: Int = -1
    lateinit var articleAdapter: ArticleListAdapter

    companion object {
        fun newInstance(cid: Int) =
                ProjectArticleListFragment().apply {
                    arguments = Bundle().apply {
                        putInt("cid", cid)
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
        articleAdapter = ArticleListAdapter(this!!.context!!, null, false).apply {
            setOnItemClickListener { holder, item, i ->
                ArticleActivity.start(context, item.link)
            }

            setOnItemChildClickListener(R.id.collectImg, { holder, item, i ->
                listNum = i
                if (item.collect) {
                    presenter.setUnCollect(item.id)
                }else{
                    presenter.setCollect(item.id)
                }
            })

            setOnLoadMoreListener {
                presenter.getProjectItem(pageNum, cid)
            }
        }
        var manage = LinearLayoutManager(context)
        articleList.apply {
            layoutManager = manage
            adapter = articleAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var lastVisibleItem: Int = -1
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (lastVisibleItem + 1 == articleAdapter.dataCount && pageNum - 1 != pageAllNum) {
                        presenter.getProjectItem(pageNum, cid)
                    }
                }
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                    //最后一个可见的ITEM
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                }
            })
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            pageNum = 0
            presenter.getProjectItem(pageNum, cid)
        }
    }

    override fun initLoad() {
        presenter.getProjectItem(pageNum, cid)
    }

    override fun getProjectItemSuccess(articleInfos: ArticleBean) {
        if (swipeRefreshLayout == null){
            return
        }
        swipeRefreshLayout.isRefreshing = false
        if (pageNum == 0) {
            articleAdapter.setNewData(articleInfos.datas)
        } else {
            articleAdapter.setLoadMoreData(articleInfos.datas)
        }
        pageAllNum = articleInfos.pageCount
        pageNum++

    }

    override fun getProjectItemFail(msg: String) {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun setCollectSuccess(msg: String) {
        articleAdapter.getData(listNum).collect = true
        articleAdapter.change(listNum)
        context.toast("收藏成功")
    }

    override fun setCollectFail(msg: String) {
        context.toast(msg)
    }

    override fun setUnCollectSuccess(msg: String) {
        articleAdapter.getData(listNum).collect = false
        articleAdapter.change(listNum)
        context.toast("取消成功")
    }

    override fun setUnCollectFail(msg: String) {
        context.toast(msg)
    }

}