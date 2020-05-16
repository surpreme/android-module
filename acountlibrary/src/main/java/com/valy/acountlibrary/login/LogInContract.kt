package com.valy.acountlibrary.login

import com.valy.baselibrary.baseCommon.basemvp.CommonPresenter
import com.valy.baselibrary.baseCommon.basemvp.CommonView
import java.security.Key

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class LogInContract {
    interface View : CommonView {
        fun loginS(key: String)
    }

    interface Presenter : CommonPresenter<View> {
        fun login(phone: String, psw: String)
    }

}