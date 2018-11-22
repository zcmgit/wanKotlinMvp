package com.kotlin.test.ui.home

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kotlin.test.R
import com.kotlin.test.base.fragment.BaseMvpFragment
import com.kotlin.test.bean.HomeBannerBean
import com.kotlin.test.bean.article.HomeArticleBean
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.ui.article.ArticleActivity
import com.kotlin.test.weigets.GlideImageLoader
import com.othershe.baseadapter.interfaces.OnItemClickListener
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast


/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class HomeFragment : BaseMvpFragment<HomePresenterImpl>(), HomeContract.View {

    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var bannerNew: Banner
    private var pageNum : Int = 0

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
        bannerNew = LayoutInflater.from(context).inflate(R.layout.home_header, homeLayout,false) as Banner
        bannerNew.run {
            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            setImageLoader(GlideImageLoader())
            setDelayTime(2000)
            setBannerAnimation(Transformer.Default)
            isAutoPlay(true)
            setIndicatorGravity(BannerConfig.CENTER)
            setOnBannerListener{ it ->

            }
        }
        articleListAdapter = ArticleListAdapter(context, null, true).apply {
            addHeaderView(bannerNew)
        }
        articleList?.layoutManager = LinearLayoutManager(context)
        articleList?.adapter = articleListAdapter
        articleListAdapter.setOnItemClickListener { viewHolder, dataItem, i ->
            ArticleActivity.start(this!!.context!!,dataItem.link)
        }


        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
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

    override fun getArticleSuccess(articles: HomeArticleBean) {
        swipeRefreshLayout.isRefreshing = false
        if (articleListAdapter.dataCount == 0) {
            articleListAdapter.setNewData(articles.datas)
        } else {
            articleListAdapter.setLoadMoreData(articles.datas)
        }
        if(articles.total == pageNum + 1){
            articleListAdapter.loadEnd()
        }else{
            pageNum ++
        }
    }

    override fun getArticleFail(info: String) {
        swipeRefreshLayout.isRefreshing = false
        context?.toast("获取失败")
    }
}