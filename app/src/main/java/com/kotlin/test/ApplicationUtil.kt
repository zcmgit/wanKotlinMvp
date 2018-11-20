package com.kotlin.test

import android.app.Application

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class ApplicationUtil : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: ApplicationUtil
        fun getApp(): ApplicationUtil {
            return instance
        }
    }
}
