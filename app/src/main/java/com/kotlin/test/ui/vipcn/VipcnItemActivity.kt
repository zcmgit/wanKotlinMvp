package com.kotlin.test.ui.vipcn

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.base.adapter.VipcnItemListAdapter
import com.kotlin.test.bean.vipcn.VipcnItemBean
import com.kotlin.test.ui.article.ArticleActivity
import com.kotlin.test.util.sp.SPUtil
import kotlinx.android.synthetic.main.vipcn_article_list.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
class VipcnItemActivity : BaseMvpActivity<VipcnItemPresenter>(), VipcnItemContract.View {

    private var pageNum: Int = 0
    private var pageAllNum: Int = 0
    private var id: Int = 0
    private var title: String = ""

    private var clickItem: Int = -1

    private var searchPageNum: Int = 0
    private var searchPageAllNum: Int = 0
    lateinit var vipcnItemAdapter: VipcnItemListAdapter

    companion object {
        fun start(context: Context, id: Int,title: String) {
            var intent = Intent(context, VipcnItemActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("title",title)
            context.startActivity(intent)
        }
    }

    override fun initPresenter(): VipcnItemPresenter {
        return VipcnItemPresenter(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.vipcn_article_list
    }

    override fun initData() {
        id = intent.getIntExtra("id", 0)
        title = intent.getStringExtra("title")
    }

    override fun initView() {

        //toolbar点击打开左侧菜单
        toolbar.apply {
            setNavigationOnClickListener {
                finish()
            }
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_search -> {
                        Log.d("TAG","点击----")
                        return@setOnMenuItemClickListener true
                    }
                }
                return@setOnMenuItemClickListener false
            }
            //标题搜索图标
            inflateMenu(R.menu.menu_search_view)
        }

        toolBarTitle.text = title


        var searchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        searchView!!.visibility = View.GONE
        searchView!!.run {
            clearFocus()
            onActionViewExpanded()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String): Boolean {
                    searchView!!.clearFocus()
                    if (TextUtils.isEmpty(p0)) {
                        toast("请输入内容后重试")
                    } else {
                        presenter.searchVipcnInfo(id,searchPageNum,p0)
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }

            })
        }

        var textView: SearchView.SearchAutoComplete = searchView!!.findViewById(R.id.search_src_text)
        textView.setTextColor(getResources().getColor(R.color.colorAccent))
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13.0f)
        textView.setBackgroundResource(R.drawable.rect_cccccc_ffffff_6)
        val underline = searchView!!.findViewById(R.id.search_plate) as View
        underline.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            pageNum = 0
            presenter.getVipcnInfoById(id, pageNum)
        }

        vipcnItemAdapter = VipcnItemListAdapter(context, null, true).apply {
            setOnItemClickListener { viewHolder, vipcnBean, i ->
                ArticleActivity.start(context, vipcnBean.link)
            }

            setOnItemChildClickListener(R.id.vipcnCollectImg,{ viewHolder, vipcnBean, i ->
                clickItem = i
                if(vipcnBean.collect){
                    presenter.setUnCollect(vipcnBean.id)
                }else{
                    presenter.setCollect(vipcnBean.id)
                }
            })
        }
        var lineLayoutManager = LinearLayoutManager(this)
        articleList.apply {
            layoutManager = lineLayoutManager
            adapter = vipcnItemAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var lastVisibleItem: Int = -1
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (lastVisibleItem + 1 == vipcnItemAdapter.dataCount && pageNum != pageAllNum) {
                        presenter.getVipcnInfoById(id, pageNum)
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
    }

    override fun initLoad() {
        presenter.getVipcnInfoById(id, pageNum)
    }

    override fun getVipcnInfoByIdSuccess(infos: VipcnItemBean) {
        swipeRefreshLayout.isRefreshing = false
        if(infos.datas.isNotEmpty()){
            if (pageNum == 0) {
                vipcnItemAdapter.setNewData(infos.datas)
            } else {
                vipcnItemAdapter.setLoadMoreData(infos.datas)
            }
            pageAllNum = infos.pageCount
            pageNum++
        }
    }

    override fun getVipcnInfoByIdFail(msg: String) {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun setCollectSuccess(msg: String) {
        vipcnItemAdapter.getData(clickItem).collect = true
        vipcnItemAdapter.change(clickItem)
        toast("收藏成功")
    }

    override fun setCollectFail(msg: String) {
        toast(msg)
    }

    override fun setUnCollectSuccess(msg: String) {
        vipcnItemAdapter.getData(clickItem).collect = false
        vipcnItemAdapter.change(clickItem)
        toast("取消成功")
    }

    override fun setUnCollectFail(msg: String) {
        toast(msg)
    }

    override fun searchVipcnSuccess(infos: VipcnItemBean) {
        swipeRefreshLayout.isRefreshing = false
        if(infos.datas.isNotEmpty()){
            if (searchPageNum == 0) {
                vipcnItemAdapter.setNewData(infos.datas)
            } else {
                vipcnItemAdapter.setLoadMoreData(infos.datas)
            }
            searchPageAllNum = infos.pageCount
            searchPageNum++
        }else{
            toast("未搜索到信息")
        }
    }

    override fun searchVipcnFail(msg: String) {
        swipeRefreshLayout.isRefreshing = false
    }
}