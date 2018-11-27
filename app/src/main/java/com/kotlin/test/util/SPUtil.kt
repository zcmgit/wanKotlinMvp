package com.kotlin.test.util

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class SPUtil {
    companion object {

        fun saveCookie(cookie: String) {
            SharedPreferencesHelper.putSP("cookies", cookie)
        }

        fun getCookies(): String {
            return SharedPreferencesHelper.getSP("cookies", "")
        }

        fun setUserName(name: String) {
            SharedPreferencesHelper.putSP("username", name)
        }

        fun getUserName(): String {
            return SharedPreferencesHelper.getSP("username", "")
        }

        fun remove(key: String){
            return SharedPreferencesHelper.remove(key)
        }
    }
}