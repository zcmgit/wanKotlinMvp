package com.kotlin.test.bean.article

/**
 * @author zcm
 * @create 2018/11/20
 * @Describe
 */
//"curPage":1,
//"datas":Array[20],
//"offset":0,
//"over":false,
//"pageCount":285,
//"size":20,
//"total":5692
data class HomeArticleBean(
        val over: Boolean = false,
        val pageCount: Int = 0,
        val total: Int = 0,
        val curPage: Int = 0,
        val offset: Int = 0,
        val size: Int = 0,
        val datas: List<DataItem>
)