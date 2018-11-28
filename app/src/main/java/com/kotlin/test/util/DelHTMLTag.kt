package com.kotlin.test.util

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author zcm
 * @create 2018/11/28
 * @Describe
 */
class DelHTMLTag{

    companion object {
        private var REGEX_SCRIPT: String = "<script[^>]*?>[\\s\\S]*?<\\/script>"
        private var REGEX_STYLE: String = "<style[^>]*?>[\\s\\S]*?<\\/style>"
        private var REGEX_HTML: String = "<[^>]+>"
        private var REGEX_SPACE: String = "\\s*|\t|\r|\n"
        fun delHTMLTag(html: String): String{
            // 过滤script标签
            var p_script = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE)
            var m_script = p_script.matcher(html)
            var htmlStr = m_script.replaceAll("")

            // 过滤style标签
            var p_style = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE)
            var m_style = p_style.matcher(htmlStr)
            htmlStr = m_style.replaceAll("")

            // 过滤html标签
            var p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE)
            var m_html = p_html.matcher(htmlStr)
            htmlStr = m_html.replaceAll("")

            // 过滤空格回车标签
            var p_space = Pattern.compile(REGEX_SPACE, Pattern.CASE_INSENSITIVE)
            var m_space = p_space.matcher(htmlStr)
            htmlStr = m_space.replaceAll("")
            return htmlStr
        }
    }
}