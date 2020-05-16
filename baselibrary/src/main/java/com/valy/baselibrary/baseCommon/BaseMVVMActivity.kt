package com.valy.baselibrary.baseCommon

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.valy.baselibrary.utils.screen.ScreenSizeUtils
import com.valy.baselibrary.R
import com.valy.baselibrary.utils.screen.StatusBarUtils
import com.valy.baselibrary.utils.system.LogUtils
import java.text.DecimalFormat

/**Lqayou

 * @Auther: liziyang

 * @datetime: 2020-01-18

 * @desc:
 *  .意思是这个参数可以为空,并且程序继续运行下去

 * !!.的意思是这个参数如果为空,就抛出异常

 */
abstract class BaseMVVMActivity : BaseActivity() {
    abstract fun initObserver()
    open fun initViewsDatas() {}
    override fun initDatas() {
        super.initDatas()
        initObserver()
        initViewsDatas()
    }

}