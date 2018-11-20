package com.kotlin.test.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.kotlin.test.ApplicationUtil


/**
 * 保存信息配置类
 */
object SharedPreferencesHelper {
    private val prefs: SharedPreferences by lazy {
        ApplicationUtil.getApp().applicationContext.getSharedPreferences("sp", Context.MODE_PRIVATE)
    }

    /**
     * 存储
     */
    @SuppressLint("CommitPrefEdits")
    fun putSP(name: String, value: Any) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun<T> getSP(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }
        return res as T
    }
    /**
     * 移除某个key值已经对应的值
     */
    fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    /**
     * 清除所有数据
     */
    fun clear() {
        prefs.edit().clear().apply()
    }

    /**
     * 查询某个key是否存在
     */
    fun contain(key: String): Boolean {
        return prefs.contains(key)
    }
}