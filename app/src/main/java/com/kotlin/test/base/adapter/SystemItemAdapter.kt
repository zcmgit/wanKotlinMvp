package com.kotlin.test.base.adapter

import android.content.Context
import com.kotlin.test.R
import com.kotlin.test.bean.system.SystemItemInfoBean
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class SystemItemAdapter ( context: Context, items : List<SystemItemInfoBean>,isOpenLoad : Boolean)
    : CommonBaseAdapter<SystemItemInfoBean>(context,items,isOpenLoad){
    override fun getItemLayoutId(): Int {
        return R.layout.system_child_item_list
    }

    override fun convert(p0: ViewHolder, p1: SystemItemInfoBean, p2: Int) {
        with(p0){
            setText(R.id.child_item,p1.name)
        }
    }

}