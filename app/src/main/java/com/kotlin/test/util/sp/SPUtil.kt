package com.kotlin.test.util.sp

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

        fun getHistoryInfo(): String {
            return SharedPreferencesHelper.getSP("history_web", "")
        }


        fun setHistoryInfo(info: String) {
            var historyStr = SharedPreferencesHelper.getSP("history_web", "")
            if (!historyStr.equals("")){
                if(!historyStr.split("\n").contains(info)){
                    historyStr = historyStr + "\n" + info
                    SharedPreferencesHelper.putSP("history_web", historyStr)
                }
            }else{
                SharedPreferencesHelper.putSP("history_web", info)
            }
        }

        fun remove(key: String) {
            return SharedPreferencesHelper.remove(key)
        }
    }
}