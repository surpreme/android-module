package com.valy.mvvm.ui.splash


import com.valy.baselibrary.utils.view.SingleClick


import android.annotation.SuppressLint
import android.os.Build
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.valy.acountlibrary.login.LogInActivity
import com.valy.baselibrary.baseCommon.AccountConstant
import com.valy.baselibrary.baseCommon.BaseActivity
import com.valy.baselibrary.baseCommon.BaseConstant
import com.valy.baselibrary.utils.db.SharePreferencesHelper
import com.valy.mvvm.R
import com.valy.mvvm.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**

 * @Auther: valy

 * @datetime: 2020/5/16

 * @desc:

 */
class SplashActivity : BaseActivity() {
    private var mCountDownTimer: CountDownTimer? = null
    private var mCountDownTimer2: CountDownTimer? = null

    override fun getLayoutId(): Int = R.layout.activity_splash
    override fun initViews() {
        super.initViews()
        splash_skip_tv.visibility = View.GONE
        val mLayoutParams = splash_title_tv.layoutParams as ConstraintLayout.LayoutParams
        mLayoutParams.topMargin = getScreenHeight() / 10
        splash_title_tv.layoutParams = mLayoutParams
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            splash_title_tv.setTextColor(getColor(R.color.black))
            splash_signature_tv.setTextColor(getColor(R.color.black))
        }

    }

    override fun onClick() {
        super.onClick()
        splash_skip_tv.setOnClickListener(object : SingleClick() {
            override fun onSingleClick(v: View?) {
                mCountDownTimer?.cancel()
                choiceStartPage()
            }

        })
    }

    override fun initDatas() {
        super.initDatas()
        if (mCountDownTimer == null)
            mCountDownTimer = object : CountDownTimer(4 * 1000, 1000) {
                @SuppressLint("DefaultLocale", "SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    splash_skip_tv.text = "${millisUntilFinished.toInt() / 1000} 跳过"

                }

                override fun onFinish() {
                    choiceStartPage()
                }
            }
        if (mCountDownTimer2 == null)
            mCountDownTimer2 = object : CountDownTimer(2 * 1000, 1000) {
                override fun onFinish() {
                    splash_skip_tv.visibility = View.VISIBLE
                    splash_background_iv.setImageDrawable(getDrawable(R.drawable.splash_background))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        splash_title_tv.setTextColor(getColor(R.color.white))
                        splash_signature_tv.setTextColor(getColor(R.color.white))
                    }
                    scaleDown(splash_background_iv)
                    mCountDownTimer?.start()


                }

                override fun onTick(millisUntilFinished: Long) {
                }

            }
        mCountDownTimer2?.start()

    }

    private fun choiceStartPage() {
        val mSharedPreferenceUtil = SharePreferencesHelper(mContext, BaseConstant.AccountManageTag)
        if (!mSharedPreferenceUtil.contain("isFirstRun")) {
            mSharedPreferenceUtil.put("isFirstRun", true)
            scaleDown(splash_background_iv)
            showToast("您第一次下载此App 感谢您的下载")
            object : CountDownTimer(1 * 1000, 1000) {
                override fun onFinish() {
                    fadeOut(splash_background_iv)
                    startActivity(LogInActivity::class.java)
                    finish()
                }

                override fun onTick(millisUntilFinished: Long) {
                }

            }.start()
        } else {
            fadeOut(splash_background_iv)
            if (mSharedPreferenceUtil.contain("KeyValue")) {
                if ((mSharedPreferenceUtil.getSharePreference(
                        "KeyValue",
                        ""
                    ) as String).isNotEmpty()
                ) {
                    AccountConstant.KEY=mSharedPreferenceUtil.getSharePreference(
                        "KeyValue",
                        ""
                    ) as String
                    startActivity(MainActivity::class.java)
                } else
                    startActivity(LogInActivity::class.java)
            } else
                startActivity(LogInActivity::class.java)
            finish()

        }
    }

    //放大动画
    private fun scaleDown(srcView: View) {
        val scaleAnimation = AnimationUtils.loadAnimation(mContext, R.anim.splash_big_anim)
        srcView.startAnimation(scaleAnimation)
    }

    //渐变动画
    private fun fadeOut(srcView: View) {
        val animateTime = 500L
        val animation = AlphaAnimation(1f, 0f)
        animation.fillAfter = true
        animation.duration = animateTime
        srcView.startAnimation(animation)
    }

    override fun onDestroy() {
        super.onDestroy()
        splash_background_iv.clearAnimation()
        mCountDownTimer?.cancel()
        mCountDownTimer2?.cancel()
    }


}