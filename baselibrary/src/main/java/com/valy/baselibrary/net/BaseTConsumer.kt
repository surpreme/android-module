package com.valy.baselibrary.net


import com.valy.baselibrary.utils.system.LogUtils
import io.reactivex.functions.Consumer
import io.reactivex.subscribers.DisposableSubscriber
import okhttp3.ResponseBody
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/18

 * @desc:

 */
abstract class BaseTConsumer<T> : DisposableSubscriber<ResponseBody?>() {
    abstract fun error(error: String)
    abstract fun success(datas: T)

     fun accept(it: ResponseBody?) {
        try {
            val jsonObject = JSONObject(it!!.string())
            val datas = jsonObject.opt("datas") as T
            val datasObject = jsonObject.optJSONObject("datas")
            if (datasObject != null) {
                val error = datasObject.optString("error")
                if (!error.isNullOrEmpty()) {
                    error(error)
                } else {
                    success(datas)

                }
            } else {
                success(datas)
            }
        } catch (e: Exception) {
            LogUtils.e(e)
        }


    }

    override fun onComplete() {

    }

    override fun onNext(t: ResponseBody?) =accept(t)

    override fun onError(t: Throwable?) {
        LogUtils.e(t)
    }


}