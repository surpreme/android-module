package com.valy.newlibrary.news

/**

 * @Auther: valy

 * @datetime: 2020/5/17

 * @desc:

 */
 class NewsBean{
    var s: String=""
    var toolTitle:String=""
    var toolRightTitle:String=""
    var title:String=""


    constructor(s: String, toolTitle: String, toolRightTitle: String, title: String) {
        this.s = s
        this.toolTitle = toolTitle
        this.toolRightTitle = toolRightTitle
        this.title = title
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewsBean

        if (s != other.s) return false
        if (toolTitle != other.toolTitle) return false
        if (toolRightTitle != other.toolRightTitle) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = s.hashCode()
        result = 31 * result + toolTitle.hashCode()
        result = 31 * result + toolRightTitle.hashCode()
        result = 31 * result + title.hashCode()
        return result
    }


}