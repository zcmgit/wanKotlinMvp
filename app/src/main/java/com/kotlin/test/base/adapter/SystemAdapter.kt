package com.kotlin.test.base.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kotlin.test.R
import com.kotlin.test.bean.eventbus.SystemItemId
import com.kotlin.test.bean.system.SystemInfoBean
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter
import org.greenrobot.eventbus.EventBus

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class SystemAdapter(context: Context, sysTemInfos: List<SystemInfoBean>?, isOpenLoad: Boolean)
    : CommonBaseAdapter<SystemInfoBean>(context, sysTemInfos, isOpenLoad) {
    var context = context
    override fun getItemLayoutId(): Int {
        return R.layout.system_info_list
    }

    override fun convert(p0: ViewHolder, p1: SystemInfoBean, p2: Int) {
        with(p0) {
            setText(R.id.system_title, p1.name)
            var adapter = SystemItemAdapter(context,p1.children,false)
            var listView = p0.getView(R.id.system_item_list) as RecyclerView
            var manage = LinearLayoutManager(context)
            manage.orientation = LinearLayoutManager.HORIZONTAL
            listView.layoutManager = manage
            listView.adapter = adapter
            adapter.setOnItemClickListener { holder, systemItemInfoBean, i ->
                EventBus.getDefault().post(SystemItemId(systemItemInfoBean.id))
            }
        }
    }
}