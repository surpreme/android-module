package com.valy.baselibrary.baseCommon.basemvp

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

interface CommonPresenter<V : CommonView> {
    fun attachView(view: V)

    fun detachView()
}
