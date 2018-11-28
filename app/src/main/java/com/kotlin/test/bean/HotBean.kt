package com.kotlin.test.bean

/**
 * @author zcm
 * @create 2018/11/28
 * @Describe
 */
data class HotBean(
        var id: Int = -1,
        var link: String = "",
        var name: String = "",
        var order: Int = 0,
        var visible: Int = 0
)