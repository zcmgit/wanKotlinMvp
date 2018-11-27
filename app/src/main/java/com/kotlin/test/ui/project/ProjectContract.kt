package com.kotlin.test.ui.home

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.ProjectBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class ProjectContract {
    interface View : BaseView {
        fun getProjectSuccess(projectBeans: List<ProjectBean>)
        fun getProjectFail(msg: String)
    }

    interface Presenter {
        fun getProjectInfo()
    }
}