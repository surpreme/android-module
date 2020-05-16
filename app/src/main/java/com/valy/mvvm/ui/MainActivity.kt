package com.valy.mvvm.ui

import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.valy.baselibrary.baseCommon.ArouterConstant
import com.valy.baselibrary.baseCommon.BaseActivity
import com.valy.baselibrary.baseCommon.BaseConstant
import com.valy.baselibrary.utils.db.SharePreferencesHelper
import com.valy.baselibrary.utils.view.SingleClick
import com.valy.mvvm.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


/**

 * @Auther: valy

 * @datetime: 2020/5/16

 * @desc:

 */
@Route(path = ArouterConstant.MainActivity)
class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun initViews() {
        super.initViews()
        setStatusBar(0)
    }

    override fun onClick() {
        clear_account_tv.setOnClickListener(object : SingleClick(2000) {
            override fun onSingleClick(v: View?) {
                val mSharedPreferenceUtil =
                    SharePreferencesHelper(mContext, BaseConstant.AccountManageTag)
                if (mSharedPreferenceUtil.all.isNotEmpty())
                    mSharedPreferenceUtil.clear()
                if (mSharedPreferenceUtil.all.isEmpty())
                    showToast("清理完毕")
            }

        })
        lottieAnimationView.setOnClickListener(object : SingleClick() {
            override fun onSingleClick(v: View?) {
                ARouter.getInstance().build(ArouterConstant.NewsActivity).navigation()
            }

        })
    }

     var exitTime: Long=0L

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK
            && event.action === KeyEvent.ACTION_DOWN
        ) {
            if (System.currentTimeMillis() - exitTime > 2000) { //中间间隔的时间,可设定
                Toast.makeText(
                    applicationContext, "再按一次退出程序",
                    Toast.LENGTH_SHORT
                ).show()
                exitTime = System.currentTimeMillis()
            } else {
                exitProcess(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}