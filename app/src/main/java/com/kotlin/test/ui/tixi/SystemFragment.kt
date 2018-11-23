package com.kotlin.test.ui.tixi

import android.support.v7.widget.LinearLayoutManager
import com.kotlin.test.R
import com.kotlin.test.base.adapter.SystemAdapter
import com.kotlin.test.base.fragment.BaseMvpFragment
import com.kotlin.test.bean.article.HomeArticleBean
import com.kotlin.test.bean.eventbus.SystemItemId
import com.kotlin.test.bean.system.SystemInfoBean
import kotlinx.android.synthetic.main.fragment_system.*
import kotlinx.android.synthetic.main.system_info_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class SystemFragment : BaseMvpFragment<SystemPresenterImpl>(),SystemContract.View{


    private lateinit var adapter : SystemAdapter
    companion object {
        fun newInstance() = SystemFragment()
    }

    override fun initPresenter(): SystemPresenterImpl {
        return SystemPresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.fragment_system
    }

    override fun initData() {
    }

    override fun initView() {
        EventBus.getDefault().register(this)
        adapter = SystemAdapter(this!!.context!!,null,false)
        var manage = LinearLayoutManager(context)
        articleList.layoutManager = manage
        articleList.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            presenter.getSystemInfo()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun SystemItemEvent(event: SystemItemId) {
        SystemArticleListActivity.start(this!!.context!!,event.id)
    }

    override fun initLoad() {
        presenter.getSystemInfo()
    }

    override fun getSystemSuccess(bean: List<SystemInfoBean>) {
        swipeRefreshLayout.isRefreshing = false
        adapter.setNewData(bean)
    }

    override fun getSystemFail(msg: String) {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}