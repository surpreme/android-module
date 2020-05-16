package com.valy.baselibrary.baseCommon.basemvp

import android.content.Context

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

interface CommonView {
    fun getContext(): Context
    fun showError(msg: String)
    fun Untoken()
    fun closeLoading()
    fun showLoading()

}
