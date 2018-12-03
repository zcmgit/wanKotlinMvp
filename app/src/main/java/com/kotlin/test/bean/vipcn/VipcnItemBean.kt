package com.kotlin.test.bean.vipcn

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
data class VipcnItemBean(
    var curPage: Int = -1,
    var datas: List<VipcnItemDatasBean> = arrayListOf(),
    var offset:Int = -1,
    var over: Boolean = false,
    var pageCount: Int = -1,
    var size: Int = -1,
    var total: Int = -1
)