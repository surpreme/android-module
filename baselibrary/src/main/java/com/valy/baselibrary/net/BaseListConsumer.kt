package com.valy.baselibrary.net


import com.valy.baselibrary.utils.system.LogUtils
import io.reactivex.functions.Consumer
import okhttp3.ResponseBody
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/18

 * @desc:

 */
abstract class BaseListConsumer : Consumer<ResponseBody?> {
    private var isInside: Boolean = true
    abstract fun error(error: String)
    abstract fun success(datas: JSONObject, isHas: Boolean)

    constructor()
    constructor(isInside: Boolean) {
        this.isInside = isInside
    }

    override fun accept(it: ResponseBody?) {
        try {
            val jsonObject = JSONObject(it!!.string())
            val datas = jsonObject.optJSONObject("datas")
            if (datas != null) {
                val error = datas.optString("error")
                if (!error.isNullOrEmpty()) {
                    error(error)
                } else {
                    val hasmore: Boolean? = if (isInside)
                        jsonObject.optBoolean("hasmore")
                    else
                        datas.optBoolean("hasmore")
                    if (hasmore != null) {
                        success(datas, hasmore)
                    } else
                        success(datas, false)
                }
            }
        } catch (e: Exception) {
            LogUtils.e(e)
        }


    }


}