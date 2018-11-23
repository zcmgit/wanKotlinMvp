package com.kotlin.test.bean.system

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
data class SystemInfoBean (
        var children : List<SystemItemInfoBean> = arrayListOf(),
        var courseId : Int = 0,
        var id : Int = 0,
        var name : String = "",
        var order : Int = 0,
        var parentChapterId : Int = 0,
        var userControlSetTop : Boolean = false,
        var visible : Int = 0
)