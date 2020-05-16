package com.valy.baselibrary.baseCommon.basemvp

import android.content.Context
import android.widget.Toast
import com.valy.baselibrary.baseCommon.BaseActivity
import com.valy.baselibrary.dialog.LoadingDialogFragment
import com.valy.baselibrary.utils.system.LogUtils
import java.lang.reflect.ParameterizedType

/**

 * @Auther: liziyang

 * @datetime: 2020-01-19

 * @desc:

 */
abstract class CommonMVPActivity<V : CommonView, T : CommonPresenterImpl<V>> : BaseActivity(),
    CommonView {
    open fun onDestroys() {
        if (mPresenter != null)
        lifecycle.removeObserver(mPresenter!!)
    }

    var mPresenter: T? = null

    override fun getContext(): Context {
        return this
    }

    override fun Untoken() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initExtra() {
        super.initExtra()
        mPresenter = getInstance<T>(this, 1)
        mPresenter!!.attachView(this as V)
        lifecycle.addObserver(mPresenter!!)//添加LifecycleObserver

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null)
            mPresenter!!.detachView()
        onDestroys()
    }

    fun <T> getInstance(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                .newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }

    var mLoadingDialogFragment: LoadingDialogFragment? = null
    override fun showLoading() {
        mLoadingDialogFragment = LoadingDialogFragment()
        mLoadingDialogFragment?.show(supportFragmentManager, "LoadingDialogFragment")
    }

    override fun onStop() {
        super.onStop()
        closeLoading()

    }

    override fun closeLoading() {
        if (mLoadingDialogFragment != null) {
            mLoadingDialogFragment?.dismissAllowingStateLoss()
        }
    }

    override fun showError(msg: String) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show()
        LogUtils.e(msg + this.localClassName)
    }


}