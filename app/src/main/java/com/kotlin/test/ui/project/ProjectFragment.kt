package com.kotlin.test.ui.home

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.test.R
import com.kotlin.test.base.adapter.ArticleListAdapter
import com.kotlin.test.base.adapter.SystemAdapter
import com.kotlin.test.base.adapter.ViewPagerAdapter
import com.kotlin.test.base.fragment.BaseMvpFragment
import com.kotlin.test.bean.ProjectBean
import com.kotlin.test.ui.project.ProjectArticleListFragment
import kotlinx.android.synthetic.main.fragment_project.*
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class ProjectFragment : BaseMvpFragment<ProjectPresenterImpl>(),ProjectContract.View, TabLayout.OnTabSelectedListener{
    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun initPresenter(): ProjectPresenterImpl {
        return ProjectPresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.fragment_project
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initLoad() {
        presenter.getProjectInfo()
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
    }

    //获取项目信息成功，加载TabLayout
    override fun getProjectSuccess(projectBeans: List<ProjectBean>) {
        var titles = arrayListOf<String>()
        var fragments = arrayListOf<Fragment>()
        for (project in projectBeans){
            titles.add(project.name)
            fragments.add(ProjectArticleListFragment.newInstance(project.id))
        }

        var adapter = ViewPagerAdapter(this.childFragmentManager)
        adapter.setFragmentAndTitles(fragments,titles)
        view_pager.adapter =  adapter//让tab和viewpager关联起来
        view_pager.offscreenPageLimit = projectBeans.size
        tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
        tab_layout.setupWithViewPager(this.view_pager)
    }

    override fun getProjectFail(msg: String) {
    }
}