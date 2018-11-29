package com.kotlin.test.ui.main

import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.widget.RelativeLayout
import android.widget.TextView
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import com.kotlin.test.base.adapter.ViewPagerAdapter
import com.kotlin.test.base.dialog.LogoutDialog
import com.kotlin.test.ui.about.AboutActivity
import com.kotlin.test.ui.collect.CollectActivity
import com.kotlin.test.ui.home.HomeFragment
import com.kotlin.test.ui.home.ProjectFragment
import com.kotlin.test.ui.login.LoginActivity
import com.kotlin.test.ui.search.SearchActivity
import com.kotlin.test.ui.tixi.SystemFragment
import com.kotlin.test.util.sp.SPUtil
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class MainHomeActivity : BaseMvpActivity<MainHomePresenterImpl>(), MainHomeContract.View {

    private lateinit var userNameTxt: TextView

    companion object {
        fun start(context: Context){
            var intent = Intent(context,MainHomeActivity::class.java)
            context.startActivity(intent)
        }
    }

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
        toolbar.apply {
            setNavigationOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            //标题搜索图标
            inflateMenu(R.menu.menu_toolbar)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_search -> {
                        showSearch()
                        return@setOnMenuItemClickListener true
                    }
                }
                return@setOnMenuItemClickListener false
            }
        }

        //viewpager初始化
        var fragments = arrayListOf<Fragment>().apply {
            add(HomeFragment.newInstance())
            add(SystemFragment.newInstance())
            add(ProjectFragment.newInstance())
        }
        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setFragments(fragments)
        viewPager.apply {
            adapter = viewPagerAdapter
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(p0: Int) {

                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

                }

                override fun onPageSelected(p0: Int) {
                    bottomNav.getMenu().getItem(p0).setChecked(true)
                    toolBarTitle.text = bottomNav.getMenu().getItem(p0).title
                }

            })
        }
        toolBarTitle.text = "首页"
        //底部菜单导航
        bottomNav.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener)

        //侧滑菜单：
        navigetion_view.setNavigationItemSelectedListener { item ->
            drawerLayout.closeDrawer(GravityCompat.START)
            when (item.itemId) {
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

        userNameTxt = navigetion_view.getHeaderView(0).findViewById(R.id.usernameTxt)
        if(SPUtil.getUserName().equals("")){
            userNameTxt.text = "未登录"
        }else{
            userNameTxt.text = SPUtil.getUserName()
        }
        var headLayout = navigetion_view.getHeaderView(0).findViewById(R.id.headLayout) as RelativeLayout
        headLayout.setOnClickListener {
            if (SPUtil.getUserName().equals("")) {
                LoginActivity.start(this)
                finish()
            }
        }
    }

    override fun initLoad() {
    }

    private var OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        toolBarTitle.text = item.title
        when (item.itemId) {
            R.id.menu_home -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_tixi -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_project -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun showLoginOutDialog() {
        LogoutDialog.show(supportFragmentManager,object : LogoutDialog.OnLogoutListener{
            override fun logout() {
                presenter.logout()
            }

        })
    }

    fun showCollect() {
        CollectActivity.start(this)
    }

    fun showAbout() {
        AboutActivity.start(this)
    }

    fun showSearch() {
        SearchActivity.start(this)
    }


    override fun logoutSuccess(msg: String) {
        SPUtil.remove("username")
        SPUtil.remove("cookies")
        finish()
    }

    override fun logoutFail(msg: String) {
        toast(msg)
    }
}
