package com.valy.baselibrary.baseCommon.basemvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.valy.baselibrary.utils.system.LogUtils
import org.jetbrains.annotations.NotNull


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

open class CommonPresenterImpl<V : CommonView> : CommonPresenter<V>, LifecycleObserver {
    protected var mView: V? = null
    override fun attachView(view: V) {
        mView = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate(@NotNull owner: LifecycleOwner) {
        LogUtils.e("onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy(@NotNull owner: LifecycleOwner) {
        LogUtils.e("onDestroy")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open fun onLifecycleChanged(
        @NotNull owner: LifecycleOwner,
        @NotNull event: Lifecycle.Event
    ) {
        LogUtils.e("onLifecycleChanged")

    }

    override fun detachView() {
        mView = null
    }

    fun showError(s: String) {
        mView?.showError(s)
    }
}
