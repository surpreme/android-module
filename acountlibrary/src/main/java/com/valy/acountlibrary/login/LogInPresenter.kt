package com.valy.acountlibrary.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.valy.baselibrary.net.GsonBuilders
import com.valy.acountlibrary.net.RetrofitAcountFactory
import com.valy.baselibrary.baseCommon.basemvp.CommonPresenterImpl
import com.valy.baselibrary.net.BaseConsumer
import com.valy.baselibrary.net.RxScheduler
import com.valy.baselibrary.utils.system.LogUtils
import org.jetbrains.annotations.NotNull
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class LogInPresenter : CommonPresenterImpl<LogInContract.View>(), LogInContract.Presenter {
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
   override fun onDestroy(@NotNull owner: LifecycleOwner) {
       super.onDestroy(owner)

    }
    @SuppressLint("CheckResult")
    override fun login(phone: String, psw: String) {
        RetrofitAcountFactory.build().logIn(phone, psw, "android")
            .compose(RxScheduler.Flo_io_main())
            .subscribe(object : BaseConsumer() {
                override fun error(error: String) {
                    mView?.showError(error)
                    mView?.closeLoading()
                }

                override fun success(datas: JSONObject) {
                    mView?.loginS(datas.optString("key"))
                    mView?.closeLoading()
                }

            })
    }

}