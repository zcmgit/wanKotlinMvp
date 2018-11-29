package com.kotlin.test.ui.home

import android.support.v7.widget.LinearLayoutManager
import com.kotlin.test.R
import com.kotlin.test.base.fragment.BaseMvpFragment
import com.kotlin.test.bean.HomeBannerBean
import com.kotlin.test.bean.article.ArticleBean
import android.view.LayoutInflater
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.bean.article.DataItem
import com.kotlin.test.bean.eventbus.SystemItemId
import com.kotlin.test.ui.article.ArticleActivity
import com.kotlin.test.weigets.GlideImageLoader
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.toast


/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class HomeFragment : BaseMvpFragment<HomePresenterImpl>(), HomeContract.View {
    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var bannerNew: Banner
    private var pageNum: Int = 0
    private var changeCollectNum: Int = -1

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initPresenter(): HomePresenterImpl {
        return HomePresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {

    }

    override fun initView() {
        bannerNew = LayoutInflater.from(context).inflate(R.layout.home_header, homeLayout, false) as Banner
        bannerNew.run {
            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            setImageLoader(GlideImageLoader())
            setDelayTime(2000)
            setBannerAnimation(Transformer.Default)
            isAutoPlay(true)
            setIndicatorGravity(BannerConfig.CENTER)
            setOnBannerListener { it ->

            }
        }
        articleListAdapter = ArticleListAdapter(context, null, true).apply {
            addHeaderView(bannerNew)

            setOnItemClickListener { viewHolder, dataItem, i ->
                ArticleActivity.start(context, dataItem.link)
            }

            setOnItemChildClickListener(R.id.collectImg, { viewHolder, dataItem, i ->
                changeCollectNum = i
                if (dataItem.collect) {
                    presenter.setUnCollect(dataItem.id)
                } else {
                    presenter.setCollect(dataItem.id)
                }
            })

            setOnLoadMoreListener {
                presenter.getArticle(pageNum)
            }
        }
        articleList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleListAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            pageNum = 0
            presenter.getArticle(pageNum)
        }
    }

    override fun initLoad() {
        presenter.getArticle(0)
        presenter.getBanner()
    }

    override fun getBannerSuccess(bannerBean: List<HomeBannerBean>) {
        var imageList = arrayListOf<String>()
        var title = arrayListOf<String>()
        for (bean in bannerBean) {
            imageList.add(bean.imagePath)
            title.add(bean.title)
        }
        bannerNew.run {
            setImages(imageList)
            setBannerTitles(title)
            start()
        }

    }

    override fun getBannerFail(info: String) {
        context?.toast("获取失败")
    }

    override fun getArticleSuccess(articles: ArticleBean) {
        swipeRefreshLayout.isRefreshing = false
        if (pageNum == 0) {
            articleListAdapter.setNewData(articles.datas)
        } else {
            articleListAdapter.setLoadMoreData(articles.datas)
        }
        if (articles.pageCount == pageNum + 1) {
            articleListAdapter.loadEnd()
        } else {
            pageNum++
        }
    }

    override fun getArticleFail(info: String) {
        swipeRefreshLayout.isRefreshing = false
        context?.toast("获取失败")
    }

    override fun setCollectSuccess(msg: String) {
        articleListAdapter.getData(changeCollectNum).collect = true
        articleListAdapter.change(changeCollectNum + 1)
        context?.toast("收藏成功")
    }

    override fun setCollectFail(msg: String) {
        context?.toast(msg)
    }

    override fun setUnCollectFail(msg: String) {
        context?.toast(msg)
    }

    override fun setUnCollectSuccess(msg: String) {
        articleListAdapter.getData(changeCollectNum).collect = false
        articleListAdapter.change(changeCollectNum + 1)
        context?.toast("取消成功")
    }
}