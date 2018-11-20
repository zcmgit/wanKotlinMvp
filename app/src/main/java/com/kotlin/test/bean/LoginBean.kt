package com.kotlin.test.bean

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
data class LoginBean(
        var id: Int,
        var username: String,
        var password: String,
        var icon: String?,
        var type: Int,
        var collectIds: List<Int>?
)