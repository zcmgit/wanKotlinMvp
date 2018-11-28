package com.kotlin.test.bean

/**
 * @author zcm
 * @create 2018/11/28
 * @Describe
 */
data class WebBean(
        var icon: String = "",
        var id: Int = -1,
        var link: String = "",
        var name: String = "",
        var order: Int = -1,
        var visible: Int = 0
)