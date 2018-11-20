package com.kotlin.test.base

import android.app.Activity

import java.util.ArrayList
import java.util.Stack

class ActivityStackManager private constructor() {

    init {
        mActivityStack = Stack()
    }

    /**
     * 入栈
     *
     * @param activity
     */
    fun addActivity(activity: Activity) {
        mActivityStack.push(activity)
    }

    /**
     * 出栈
     *
     * @param activity
     */
    fun removeActivity(activity: Activity) {
        mActivityStack.remove(activity)
    }

    /**
     * 彻底退出
     */
    fun finishAllActivity() {
        var activity: Activity?
        while (!mActivityStack.empty()) {
            activity = mActivityStack.pop()
            if (activity != null) {
                activity.finish()
            }
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in mActivityStack) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 查找栈中是否存在指定的activity
     *
     * @param cls
     * @return
     */
    fun checkActivity(cls: Class<*>): Boolean {
        for (activity in mActivityStack) {
            if (activity.javaClass == cls) {
                return true
            }
        }
        return false
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            mActivityStack.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * finish指定的activity之上所有的activity
     *
     * @param actCls
     * @param isIncludeSelf
     * @return
     */
    fun finishToActivity(actCls: Class<out Activity>, isIncludeSelf: Boolean): Boolean {
        val buf = ArrayList<Activity>()
        val size = mActivityStack.size
        var activity: Activity? = null
        for (i in size - 1 downTo 0) {
            activity = mActivityStack[i]
            if (activity!!.javaClass.isAssignableFrom(actCls)) {
                for (a in buf) {
                    a.finish()
                }
                return true
            } else if (i == size - 1 && isIncludeSelf) {
                buf.add(activity)
            } else if (i != size - 1) {
                buf.add(activity)
            }
        }
        return false
    }

    /**
     * finish指定activity之上的activity
     *
     * @param actCls
     */
    fun finishTopActivity(actCls: Class<out Activity>) {
        val size = mActivityStack.size
        for (i in size - 1 downTo 0) {
            if (mActivityStack[i].javaClass.simpleName != actCls.simpleName) {
                mActivityStack[i].finish()
            } else {
                break
            }
        }
    }

    companion object {

        private var mInstance: ActivityStackManager? = null
        private lateinit var mActivityStack: Stack<Activity>

        val instance: ActivityStackManager
            get() {
                if (null == mInstance) {
                    synchronized(ActivityStackManager::class.java) {
                        if (null == mInstance) {
                            mInstance = ActivityStackManager()
                        }
                    }
                }
                return this!!.mInstance!!
            }
    }
}
