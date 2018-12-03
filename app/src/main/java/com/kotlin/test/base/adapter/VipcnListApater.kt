package com.kotlin.test.base.adapter

import android.content.Context
import com.kotlin.test.R
import com.kotlin.test.bean.vipcn.VipcnBean
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
class VipcnListApater(context: Context, list: List<VipcnBean>?, isLoadMore: Boolean):
        CommonBaseAdapter<VipcnBean>(context,list,isLoadMore){
    override fun getItemLayoutId(): Int {
        return R.layout.vipcn_list_item
    }

    override fun convert(p0: ViewHolder, p1: VipcnBean, p2: Int) {
        with(p0){
            setText(R.id.vipcnTitle,p1.name)
        }
    }

}