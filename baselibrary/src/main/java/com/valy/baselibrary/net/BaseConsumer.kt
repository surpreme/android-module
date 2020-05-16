package com.valy.baselibrary.net


import com.valy.baselibrary.utils.system.LogUtils
import io.reactivex.subscribers.DisposableSubscriber
import okhttp3.ResponseBody
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/18

 * @desc: 再次

 */
abstract class BaseConsumer : DisposableSubscriber<ResponseBody?>() {
    abstract fun error(error: String)
    abstract fun success(datas: JSONObject)
    open fun againLogIn() {}

     fun accept(it: ResponseBody?) {
        try {
            val jsonObject = JSONObject(it!!.string())
            val datas = jsonObject.optJSONObject("datas")
            val token_expired = jsonObject.optJSONObject("token_expired")
            val login = jsonObject.optJSONObject("token_expired")
            if (datas != null) {
                val error = datas.optString("error")
                if (!error.isNullOrEmpty()) {
                    if (login != null || token_expired != null) {
                        againLogIn()
                        return
                    }
                    error(error)
                } else {
                    success(datas)

                }
            }
        } catch (e: Exception) {
            LogUtils.e(e)
        }


    }

    override fun onComplete() {

    }

    override fun onNext(t: ResponseBody?)=accept(t)

    override fun onError(t: Throwable) {
        LogUtils.e(t)
    }


}