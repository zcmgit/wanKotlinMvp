package com.kotlin.test.base.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.DialogTitle

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager){

    private lateinit var fragments : List<Fragment>
    private lateinit var titles : List<String>
    fun setFragments(fragments : List<Fragment>){
        this.fragments = fragments
    }

    fun setFragmentAndTitles(fragments : List<Fragment>,titles : List<String>){
        this.fragments = fragments
        this.titles = titles
    }
    override fun getItem(p0: Int): Fragment {
        return fragments.get(p0)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }
}