package com.kotlin.test.bean.vipcn

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
data class VipcnBean(
        var children: List<String> = arrayListOf(),
        var courseId: Int = -1,
        var id:Int = -1,
        var name: String = "",
        var order: Int = -1,
        var parentChapterId: Int = -1,
        var userControlSetTop: Boolean = false,
        var visible: Int = -1
)

