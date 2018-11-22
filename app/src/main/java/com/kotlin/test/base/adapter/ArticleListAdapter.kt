package com.kotlin.test.base.adapter

import android.content.Context
import com.kotlin.test.R
import com.kotlin.test.bean.article.DataItem
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter

/**
 * @author zcm
 * @create 2018/11/20
 * @Describe
 */
class ArticleListAdapter (context: Context?, datas: List<DataItem>?, isLoadMore: Boolean)
    : CommonBaseAdapter<DataItem>(context,datas,isLoadMore){
    override fun getItemLayoutId(): Int {
        return R.layout.home_list_item
    }

    override fun convert(p0: ViewHolder, p1: DataItem, p2: Int) {
        with(p0){
            setText(R.id.title,p1.title)
        }
    }


}
