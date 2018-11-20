package com.kotlin.test.bean

/**
 * @author zcm
 * @create 2018/11/20
 * @Describe
 */
data class HomeBannerBean(
        var desc : String = "",
        var id : Int = 0,
        var imagePath : String = "",
        var isVisible : Int = 0,
        var order : Int = 0,
        var title : String = "",
        var type : Int = 0,
        var url : String = "")
