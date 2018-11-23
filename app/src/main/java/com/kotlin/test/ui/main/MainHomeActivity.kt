package com.kotlin.test.ui.main

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.base.adapter.ViewPagerAdapter
import com.kotlin.test.ui.about.AboutActivity
import com.kotlin.test.ui.collect.CollectActivity
import com.kotlin.test.ui.home.HomeFragment
import com.kotlin.test.ui.home.ProjectFragment
import com.kotlin.test.ui.search.SearchActivity
import com.kotlin.test.ui.tixi.SystemFragment
import kotlinx.android.synthetic.main.main_activity.*

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class MainHomeActivity : BaseMvpActivity<MainHomePresenterImpl>(), MainHomeContract.View {
    override fun initPresenter(): MainHomePresenterImpl {
        return MainHomePresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.main_activity
    }

    override fun initData() {
    }



    override fun initView() {
        //toolbar点击打开左侧菜单
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        //标题搜索图标
        toolbar.inflateMenu(R.menu.menu_toolbar)

        toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.action_search -> {
                    showSearch()
                    return@setOnMenuItemClickListener true
                }
            }
            return@setOnMenuItemClickListener false
        }

        //viewpager初始化
        var fragments = arrayListOf<Fragment>().apply {
            add(HomeFragment.newInstance())
            add(SystemFragment.newInstance())
            add(ProjectFragment.newInstance())
        }
        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setFragments(fragments)
        viewPager.adapter = viewPagerAdapter
        toolBarTitle.text = "首页"
        //底部菜单导航
        bottomNav.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener)

        //侧滑菜单：
        navigetion_view.setNavigationItemSelectedListener { item ->
            drawerLayout.closeDrawer(GravityCompat.START)
            when(item.itemId){
                R.id.nav_item_collect -> {//收藏
                    showCollect()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_item_about -> {//关于
                    showAbout()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_item_login_out -> {//退出
                    showLoginOutDialog()
                    return@setNavigationItemSelectedListener true
                }
            }
            return@setNavigationItemSelectedListener false
        }
    }

    override fun initLoad() {
    }

    private var OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{ item ->
        toolBarTitle.text = item.title
        when (item.itemId){
            R.id.menu_home -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_tixi -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_hot ->{
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun showLoginOutDialog(){

    }

    fun showCollect(){
        CollectActivity.start(this)
    }

    fun showAbout(){
        AboutActivity.start(this)
    }

    fun showSearch(){
        SearchActivity.start(this)
    }
}
