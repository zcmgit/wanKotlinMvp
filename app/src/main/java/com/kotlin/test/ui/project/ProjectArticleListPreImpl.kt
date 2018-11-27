package com.kotlin.test.ui.project

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.ProjectBean
import com.kotlin.test.bean.article.ArticleBean
import com.kotlin.test.ui.home.ProjectContract

/**
 * @author zcm
 * @create 2018/11/27
 * @Describe
 */
class ProjectArticleListPreImpl(view: ProjectArticleListContract.View) : BasePresenter<ProjectArticleListContract.View>(view), ProjectArticleListContract.Presenter {
    override fun getProjectItem(pageNum: Int, cid: Int) {
        RequestManager.execute(this,RetrofitManager.create(Api :: class.java).getProjectListInfo(pageNum, cid),
                object : BaseObserver<ArticleBean>(){
                    override fun onSuccess(t: ArticleBean) {
                        view.getProjectItemSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getProjectItemFail(msg)
                    }

                })
    }

}