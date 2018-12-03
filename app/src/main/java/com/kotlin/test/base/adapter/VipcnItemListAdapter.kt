package com.kotlin.test.base.adapter

import android.content.Context
import com.kotlin.test.R
import com.kotlin.test.bean.vipcn.VipcnItemDatasBean
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
class VipcnItemListAdapter(context: Context,infos: List<VipcnItemDatasBean>?,isLoadMore: Boolean):
        CommonBaseAdapter<VipcnItemDatasBean>(context,infos,isLoadMore){
    override fun getItemLayoutId(): Int {
        return R.layout.vipcn_article_list_item
    }

    override fun convert(p0: ViewHolder, p1: VipcnItemDatasBean, p2: Int) {
        with(p0){
            setText(R.id.itemTitle,p1.title)
            setText(R.id.itemTime,p1.niceDate)
            if (p1.collect){
                setBgResource(R.id.vipcnCollectImg,R.mipmap.collect_select)
            }else{
                setBgResource(R.id.vipcnCollectImg,R.mipmap.collect_unselect)
            }
        }
    }

}