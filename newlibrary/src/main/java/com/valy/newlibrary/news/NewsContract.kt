package com.valy.newlibrary.news

import com.valy.baselibrary.baseCommon.basemvp.CommonPresenter
import com.valy.baselibrary.baseCommon.basemvp.CommonView
import java.security.Key

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class NewsContract {
    interface View : CommonView {
    }

    interface Presenter : CommonPresenter<View> {
    }

}