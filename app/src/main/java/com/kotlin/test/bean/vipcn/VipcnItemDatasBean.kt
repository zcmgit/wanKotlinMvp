package com.kotlin.test.bean.vipcn

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
data class VipcnItemDatasBean(
        var apkLink: String = "",
        var author: String = "",
        var chapterId: Int = -1,
        var collect: Boolean = false,
        var courseId: Int = -1,
        var desc: String = "",
        var envelopePic: String = "",
        var fresh: Boolean = false,
        var id: Int = -1,
        var link: String = "",
        var niceDate: String = "",
        var origin: String = "",
        var projectLink: String = "",
        var publishTime: Long = -1,
        var superChapterId: Int = -1,
        var superChapterName: String = "",
        var tags: List<VipcnItemtagsBean> = arrayListOf(),
        var title: String = "",
        var type: Int = -1,
        var userId: Int = -1,
        var zan: Int = -1
)