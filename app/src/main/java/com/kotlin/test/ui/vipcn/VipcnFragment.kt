package com.kotlin.test.ui.vipcn

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.test.R
import com.kotlin.test.base.adapter.VipcnListApater
import com.kotlin.test.base.fragment.BaseMvpFragment
import com.kotlin.test.bean.vipcn.VipcnBean
import com.kotlin.test.ui.home.ProjectFragment
import com.kotlin.test.weigets.RecycleViewDivider
import kotlinx.android.synthetic.main.vipcn_fragment.*

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
class VipcnFragment : BaseMvpFragment<VipcnPresenterImpl>(), VipcnContract.View {

    private lateinit var vipcnListApater: VipcnListApater
    companion object {
        fun newInstance() = VipcnFragment()
    }
    override fun initPresenter(): VipcnPresenterImpl {
        return VipcnPresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.vipcn_fragment
    }

    override fun initData() {
    }

    override fun initView() {
        vipcnListApater = VipcnListApater(context,null,false).apply {
            setOnItemClickListener { viewHolder, vipcnBean, i ->
                VipcnItemActivity.start(context,vipcnBean.id,vipcnBean.name)
            }
        }
        var gridLayoutManager = GridLayoutManager(getContext(), 3)
        vipcnList.apply {
            layoutManager = gridLayoutManager
            adapter = vipcnListApater
//            addItemDecoration(RecycleViewDivider(context,LinearLayoutManager.VERTICAL,2,getResources().getColor(R.color.cl_ffffff)))
//            addItemDecoration(RecycleViewDivider(context,LinearLayoutManager.HORIZONTAL,2,getResources().getColor(R.color.cl_ffffff)))
        }

    }

    override fun initLoad() {
        presenter.getVipcnInfos()
    }

    override fun getVipcnSuccess(infos: List<VipcnBean>) {
        vipcnListApater.setNewData(infos)
    }

    override fun getVipcnFail(msg: String) {
    }

}