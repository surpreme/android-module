package com.valy.baselibrary.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment

/**

 * @Auther: valy

 * @datetime: 2020/3/16

 * @desc:

 */
abstract class BaseDialogFragment : DialogFragment() {
    var mContext: Context? = null

    abstract fun setContentView(): Int
    abstract fun initViews(view: View)
    open fun initDatas() {}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initDatas()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mContext = activity
        val mBaseView: View = inflater.inflate(setContentView(), container, false)
        return mBaseView
    }
}