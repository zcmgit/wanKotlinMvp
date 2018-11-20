package com.kotlin.test.ui.search

import android.content.Context
import android.content.Intent
import android.support.v7.widget.SearchView
import android.view.Menu
import com.kotlin.test.R
import com.kotlin.test.base.activity.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_search.*

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class SearchActivity : BaseMvpActivity<SearchPresenter>(),SearchContract.View{

    private var searchView : SearchView ? = null

    companion object {
        fun start(context: Context){
            var intent = Intent(context,SearchActivity::class.java)
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
    }

    override fun initView() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.inflateMenu(R.menu.menu_search_view)
        searchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        searchView!!.isIconified = false
        searchView!!.setIconifiedByDefault(false)
        searchView!!.onActionViewExpanded()

    }

    override fun initLoad() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_search_view,menu)
        searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView!!.isIconified = false
        return true
    }
}