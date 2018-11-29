package com.kotlin.test.ui.collect

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.base.adapter.CollectListAdapter
import com.kotlin.test.bean.article.ArticleBean
import com.kotlin.test.ui.article.ArticleActivity
import kotlinx.android.synthetic.main.fragment_system.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class CollectActivity : BaseMvpActivity<CollectPresentImpl>(), CollectContract.View {

    lateinit var collectListAdapter: CollectListAdapter
    private var pageNum: Int = 0
    private var collectItemNum = -1

    private var pageAllNum: Int = -1

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

        collectListAdapter = CollectListAdapter(this!!.context!!, null, false).apply {

            setOnItemClickListener { viewHolder, dataItem, i ->
                ArticleActivity.start(context, dataItem.link)
            }

            setOnItemChildClickListener(R.id.collectImg,{ viewHolder, dataItem, i ->
                collectItemNum = i
                presenter.setUnCollect(dataItem.id,dataItem.originId)
            })

        }
        var manage = LinearLayoutManager(context)
        articleList.apply {
            layoutManager = manage
            adapter = collectListAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                var lastVisibleItem: Int = -1
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (lastVisibleItem + 1 == collectListAdapter.dataCount && pageNum != pageAllNum) {
                        presenter.getCollectInfos(pageNum)
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
            presenter.getCollectInfos(pageNum)
        }

    }

    override fun initLoad() {
        presenter.getCollectInfos(pageNum)
    }

    override fun getCollectSuccess(collectInfo: ArticleBean) {
        swipeRefreshLayout.isRefreshing = false
        if (pageNum == 0) {
            collectListAdapter.setNewData(collectInfo.datas)
        } else {
            collectListAdapter.setLoadMoreData(collectInfo.datas)
        }
        pageAllNum = collectInfo.pageCount
        pageNum++
    }

    override fun getCollectFail(msg: String) {
        swipeRefreshLayout.isRefreshing = false
        toast(msg)
    }

    override fun setUnCollectSuccess(msg: String) {
        EventBus.getDefault().post(collectListAdapter.allData[collectItemNum])
        collectListAdapter.remove(collectItemNum)
        toast("取消成功")
    }

    override fun setUnCollectFail(msg: String) {
        toast(msg)
    }
}