package com.kotlin.test.bean

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
data class RegisterBean(val password: String = "",
                        val icon: String = "",
                        val id: Int = 0,
                        val type: Int = 0,
                        val email: String = "",
                        val token: String = "",
                        val username: String = "")