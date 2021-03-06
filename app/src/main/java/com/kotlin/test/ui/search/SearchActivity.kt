package com.kotlin.test.ui.search

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.TypedValue
import android.view.Menu
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.bean.HotBean
import com.kotlin.test.bean.WebBean
import com.kotlin.test.bean.article.ArticleBean
import kotlinx.android.synthetic.main.activity_search.*
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.ui.article.ArticleActivity
import com.kotlin.test.util.DelHTMLTag
import com.kotlin.test.util.sp.SPUtil
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import org.jetbrains.anko.toast
import java.lang.reflect.AccessibleObject.setAccessible
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView


/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class SearchActivity : BaseMvpActivity<SearchPresenter>(), SearchContract.View {

    private var searchView: SearchView? = null
    private var pageNum: Int = 0
    private var listNum: Int = -1
    private var searchInfos: String = ""

    private var pageAllNum: Int = -1


    private lateinit var articleListAdapter: ArticleListAdapter

    companion object {
        fun start(context: Context) {
            var intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initPresenter(): SearchPresenter {
        return SearchPresenter(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.activity_search
    }

    override fun initData() {
        getHistoryInfo()
    }

    override fun initView() {
        searchList.visibility = View.GONE
        toolbar.setNavigationOnClickListener {
            if (searchList.visibility != View.GONE) {
                searchList.visibility = View.GONE
                tagLayout.visibility = View.VISIBLE
                getHistoryInfo()
            } else {
                finish()
            }
        }
        toolbar.inflateMenu(R.menu.menu_search_view)
        searchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        searchView!!.run {
            clearFocus()
            onActionViewExpanded()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String): Boolean {
                    searchView!!.clearFocus()
                    if (TextUtils.isEmpty(p0)) {
                        toast("请输入内容后重试")
                    } else {
                        articleListAdapter.setNewData(arrayListOf())
                        pageNum = 0
                        searchInfos = p0
                        presenter.searchInfoByKey(pageNum, p0)
                        SPUtil.setHistoryInfo(p0)
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

        articleListAdapter = ArticleListAdapter(context, null, true).apply {
            setOnItemClickListener { viewHolder, dataItem, i ->
                ArticleActivity.start(context, dataItem.link)
            }

            setOnItemChildClickListener(R.id.collectImg, { holder, item, i ->
                listNum = i
                if (item.collect) {
                    presenter.setUnCollect(item.id)
                } else {
                    presenter.setCollect(item.id)
                }
            })
        }

        searchList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleListAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var lastVisibleItem: Int = -1
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (lastVisibleItem + 1 == articleListAdapter.dataCount && pageNum != pageAllNum) {
                        presenter.searchInfoByKey(pageNum,searchInfos)
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
        deleteIcon.setOnClickListener {
            SPUtil.remove("history_web")
            historyLayout.visibility = View.GONE
        }
    }

    override fun initLoad() {
        presenter.getHotInfos()
        presenter.getFriendWebInfos()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_search_view, menu)
        searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        return true
    }

    private fun getHistoryInfo() {
        var historyStr = SPUtil.getHistoryInfo()
        if (historyStr.equals("")) {
            historyLayout.visibility = View.GONE
        } else {
            historyLayout.visibility = View.VISIBLE
            var historyInfos = historyStr.split("\n")
            val adapter = object : TagAdapter<String>(historyInfos) {
                override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                    val view = LayoutInflater.from(this@SearchActivity).inflate(R.layout.flow_layout_item, parent, false) as TextView
                    view.text = t
                    return view
                }
            }
            historyFlowLayout.setAdapter(adapter)
            historyFlowLayout.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
                override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                    articleListAdapter.setNewData(arrayListOf())
                    pageNum = 0
                    searchInfos = historyInfos.get(position)
                    presenter.searchInfoByKey(pageNum, searchInfos)
                    return true
                }
            })
        }
    }

    override fun getHotInfosSuccess(hots: List<HotBean>) {
        var hotInfos = arrayListOf<String>()
        for (hot in hots) {
            hotInfos.add(hot.name)
        }
        val adapter = object : TagAdapter<String>(hotInfos) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val view = LayoutInflater.from(this@SearchActivity).inflate(R.layout.flow_layout_item, parent, false) as TextView
                view.text = t
                return view
            }
        }
        hotFlowLayout.setAdapter(adapter)
        hotFlowLayout.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                articleListAdapter.setNewData(arrayListOf())
                pageNum = 0
                searchInfos = hotInfos.get(position)
                presenter.searchInfoByKey(pageNum, searchInfos)
                return true
            }
        })
    }

    override fun getHotInfosFail(msg: String) {
        toast(msg)
    }

    override fun getFriendWebSuccess(webs: List<WebBean>) {
        var webInfos = arrayListOf<String>()
        for (hot in webs) {
            webInfos.add(hot.name)
        }
        val adapter = object : TagAdapter<String>(webInfos) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val view = LayoutInflater.from(this@SearchActivity).inflate(R.layout.flow_layout_item, parent, false) as TextView
                view.text = t
                return view
            }
        }
        webFlowLayout.setAdapter(adapter)
        webFlowLayout.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                ArticleActivity.start(this@SearchActivity, webs.get(position).link)
                return true
            }
        })
    }

    override fun getFriendWebFail(msg: String) {
        toast(msg)
    }

    override fun searchInfoSuccess(articles: ArticleBean) {
        tagLayout.visibility = View.GONE
        searchList.visibility = View.VISIBLE
        for (i in articles.datas.indices) {
            articles.datas[i].title = DelHTMLTag.delHTMLTag(articles.datas[i].title)
        }
        if (articleListAdapter.dataCount == 0) {
            articleListAdapter.setNewData(articles.datas)
        } else {
            articleListAdapter.setLoadMoreData(articles.datas)
        }
        pageAllNum = articles.pageCount
            pageNum++
    }

    override fun searchInfoFail(msg: String) {
    }

    override fun onBackPressed() {
        if (searchList.visibility != View.GONE) {
            searchList.visibility = View.GONE
            tagLayout.visibility = View.VISIBLE
            getHistoryInfo()
        } else {
            finish()
        }
    }

    override fun setCollectSuccess(msg: String) {
        articleListAdapter.getData(listNum).collect = true
        articleListAdapter.change(listNum)
        context.toast("收藏成功")
    }

    override fun setCollectFail(msg: String) {
        context.toast(msg)
    }

    override fun setUnCollectSuccess(msg: String) {
        articleListAdapter.getData(listNum).collect = false
        articleListAdapter.change(listNum)
        context.toast("取消成功")
    }

    override fun setUnCollectFail(msg: String) {
        context.toast(msg)
    }
}