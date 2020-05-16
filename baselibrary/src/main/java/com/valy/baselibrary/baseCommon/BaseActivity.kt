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
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutId(): Int
    open fun initViews() {}
    open fun initDatas() {}
    protected lateinit var back_iv: ImageView
    protected lateinit var toolbar_title_tv: TextView
    protected lateinit var right_title_tv: TextView
    protected lateinit var mContext: Context
    protected lateinit var toolbar_ll: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this

        initExtra()
        initViews()
        initDatas()
    }

    override fun onStart() {
        super.onStart()
        onClick()
    }

    /**
     * 为二次封装留下的初始化
     */
    open fun initExtra() {
    }

    /**
     * onclick
     */
    open fun onClick() {}

    /**
     * ->0默认为白色
     * ->1透明
     * ->string html5颜色
     */
    protected fun setStatusBar(s: Any) {
        when (s) {

            0 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    StatusBarUtils.setColor(this, getColor(R.color.white))
                }
            }
            1 -> {
                StatusBarUtils.setTransparent(mContext)
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    StatusBarUtils.setColor(this, Color.parseColor(s.toString()))
                }
            }
        }
    }


    /**
     * 系统栏的高度
     */
    protected fun getStatusBarHeight(): Int {
        return StatusBarUtils.getHeight(mContext)
    }

    /**
     * app toolbar 初始化
     */
    protected fun initToolBar(title: CharSequence) {
        initToolBar(title,0)


    }
//TODO
    protected fun initToolBar(title: CharSequence, @ColorInt color: Int) {
        back_iv = this.findViewById(R.id.iv_back)
        back_iv.setOnClickListener { onBackPressed() }
        toolbar_title_tv = this.findViewById(R.id.tv_title)
        toolbar_title_tv.text = title


    }

    protected fun initToolBar(title: CharSequence, rightTitle: CharSequence) {
        back_iv = this.findViewById(R.id.iv_back)
        back_iv.setOnClickListener { onBackPressed() }
        toolbar_title_tv = this.findViewById(R.id.tv_title)
        right_title_tv = this.findViewById(R.id.tv_right_title)
        toolbar_title_tv.text = title
        right_title_tv.text = rightTitle


    }

    open fun hideKeyboard(view: View) {
        val imm = getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    /**
     * toast
     */
    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     */
    open fun showShort(@StringRes resId: Int) {
        Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show()
    }

    /**
     * 跳转activity
     */
    fun startActivity(mClz: Class<*>) {
        startActivity(Intent(this, mClz))
    }

    fun startActivity(clz: Class<*>, bundle: Bundle) {
        val intent = Intent(this, clz)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    /**
     * 屏幕的尺寸
     */
    protected fun getScreenWidth(): Int {
        return ScreenSizeUtils.getScreenWidth(mContext)
    }

    protected fun getScreenHeight(): Int {
        return ScreenSizeUtils.getScreenHeight(mContext)
    }

    /**
     * 转换字符为小数后两位
     * 格式化，区小数后两位
     */
    protected open fun haveTwoDouble(d: Double): String {
        val df = DecimalFormat("0.00")
        return try {
            df.format(d)
        } catch (e: Exception) {
            LogUtils.e(d)
            ""
        }
    }
}