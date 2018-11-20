package com.kotlin.test.base.time

import android.os.SystemClock

class TimeManager private constructor() {
    private var differenceTime: Long = 0        //以前服务器时间 - 以前服务器时间的获取时刻的系统启动时间
    private var isServerTime: Boolean = false       //是否是服务器时间

    /**
     * 获取当前时间
     *
     * @return the time
     */
    //todo 这里可以加上触发获取服务器时间操作
    //时间差加上当前手机启动时间就是准确的服务器时间了
    val serviceTime: Long
        @Synchronized get() = if (!isServerTime) {
            System.currentTimeMillis()
        } else differenceTime + SystemClock.elapsedRealtime()

    /**
     * 时间校准
     *
     * @param lastServiceTime 当前服务器时间
     * @return the long
     */
    @Synchronized
    fun initServerTime(lastServiceTime: Long): Long {
        //记录时间差
        differenceTime = lastServiceTime - SystemClock.elapsedRealtime()
        isServerTime = true
        return lastServiceTime
    }

    companion object {
        private var instanceTime: TimeManager? = null
        fun getInstance(): TimeManager {
            if (instanceTime == null) {
                synchronized(TimeManager::class.java) {
                    if (instanceTime == null) {
                        instanceTime = TimeManager()
                    }
                }
            }
            return this!!.instanceTime!!
        }
    }

}
