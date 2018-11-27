package com.kotlin.test.bean

/**
 * @author zcm
 * @create 2018/11/27
 * @Describe
 */
data class ProjectBean(
        var children : List<String> = arrayListOf(),
        var courseId : Int = -1,
        var id : Int = 0,
        var name : String = "",
        var order : Int = 0,
        var parentChapterId : Int = 0,
        var userControlSetTop : Boolean = false,
        var visible : Int = -1
)