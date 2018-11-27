package com.kotlin.test.bean.article

/**
 * @author zcm
 * @create 2018/11/20
 * @Describe
 */
data class ArticleBean(
        val over: Boolean = false,
        val pageCount: Int = 0,
        val total: Int = 0,
        val curPage: Int = 0,
        val offset: Int = 0,
        val size: Int = 0,
        val datas: List<DataItem>
)