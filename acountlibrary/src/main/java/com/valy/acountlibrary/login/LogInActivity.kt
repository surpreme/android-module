package com.valy.acountlibrary.login

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.alibaba.android.arouter.launcher.ARouter
import com.valy.acountlibrary.R
import com.valy.baselibrary.baseCommon.AccountConstant
import com.valy.baselibrary.baseCommon.ArouterConstant
import com.valy.baselibrary.baseCommon.BaseActivity
import com.valy.baselibrary.baseCommon.BaseConstant
import com.valy.baselibrary.baseCommon.basemvp.CommonMVPActivity
import com.valy.baselibrary.utils.db.SharePreferencesHelper
import com.valy.baselibrary.utils.system.LogUtils
import com.valy.baselibrary.utils.text.SoftKeyboardUtil
import com.valy.baselibrary.utils.view.SingleClick
import io.reactivex.internal.operators.completable.CompletableTimer
import kotlinx.android.synthetic.main.activity_login.*

/**

 * @Auther: valy

 * @datetime: 2020/5/16

 * @desc:

 */
class LogInActivity : CommonMVPActivity<LogInContract.View, LogInPresenter>(), LogInContract.View {
    var mTel = ""
    override fun getLayoutId(): Int = R.layout.activity_login
    override fun initViews() {
        setStatusBar(0)

    }

    override fun onClick() {
        submit_tv.setOnClickListener(object : SingleClick() {
            override fun onSingleClick(v: View?) {
                if (phone_edit.text.toString().length >= 11) {
                    mTel = phone_edit.text.toString()
                    mPresenter?.login(mTel, "123456")
                    showLoading()
                    SoftKeyboardUtil.closeKeyboard(this@LogInActivity)
                } else
                    showToast("输入正确的手机号码 请重新再试")
            }

        })
    }

    override fun initDatas() {
        super.initDatas()
        submit_tv.isEnabled = false
        submit_tv.setBackgroundResource(R.drawable.login_sure_background_gray)
        phone_edit.afterTextChanged {
            LogUtils.d(it)
            if (it.length >= 11) {
                submit_tv.isEnabled = true
                submit_tv.setBackgroundResource(R.drawable.login_sure_background_green)

            } else {
                submit_tv.isEnabled = false
                submit_tv.setBackgroundResource(R.drawable.login_sure_background_gray)
            }
        }
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun loginS(key: String) {
        AccountConstant.KEY = key
        val mSharedPreferenceUtil = SharePreferencesHelper(mContext, BaseConstant.AccountManageTag)
        mSharedPreferenceUtil.put("KeyValue", key)
        if (AccountConstant.KEY.isEmpty() && !mSharedPreferenceUtil.contain("KeyValue")) {
            AccountConstant.KEY = key
            mSharedPreferenceUtil.put("KeyValue", key)
            object : CountDownTimer(1 * 1000, 1000) {
                override fun onFinish() {
                    ARouter.getInstance().build(ArouterConstant.MainActivity).navigation()
                    finish()
                }

                override fun onTick(millisUntilFinished: Long) {

                }

            }.start()
            return
        }
        ARouter.getInstance().build(ArouterConstant.MainActivity).navigation()
        finish()
    }
}