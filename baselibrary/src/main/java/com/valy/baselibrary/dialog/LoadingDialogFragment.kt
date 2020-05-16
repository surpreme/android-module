package com.valy.baselibrary.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.valy.baselibrary.R

/**
 * @Auther: valy
 * @datetime: 2020-02-28
 * @desc:
 */
class LoadingDialogFragment : BaseDialogFragment {
    var mCountDownTimer: CountDownTimer? = null

    override fun setContentView(): Int = R.layout.dialog_loading

    constructor(boolean: Boolean) {
        isAutoDismiss = boolean
    }

    constructor()

    var isAutoDismiss = true


    override fun initViews(view: View) {
        if (isAutoDismiss) {
            if (mCountDownTimer == null) {
                mCountDownTimer = object : CountDownTimer(1 * 1000, 1000) {
                    @SuppressLint("DefaultLocale")
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        dismiss()
                    }
                }
            }
            mCountDownTimer?.start()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mCountDownTimer != null) {
            mCountDownTimer?.cancel()
            mCountDownTimer = null
        }
    }

    /**
     * 设置位置在底部 而且设置背景为透明
     */
    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        val params = window?.attributes
        params?.gravity = Gravity.CENTER
        params?.width = WindowManager.LayoutParams.WRAP_CONTENT
        //        params.height = 700;
        window?.attributes = params
        //设置背景透明
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}