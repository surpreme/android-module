package com.valy.baselibrary.baseCommon.basemvp

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.valy.baselibrary.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.baseCommon.BaseRecyAdapter
import java.util.*

/**

 * @Auther: liziyang

 * @datetime: 2020-01-19

 * @desc:

 */
abstract class CommonMVPListActivity<V : CommonView, T : CommonPresenterImpl<V>, N> :
    CommonMVPActivity<V, T>(), CommonView {
    protected var recycler_view: RecyclerView? = null
    abstract fun setAdapter(): BaseRecyAdapter<N>?
    open fun setLayoutManager(): LinearLayoutManager =
        LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

    open fun addItemDecoration(): ItemDecoration = BaseItemDecoration(mContext)
    open fun getRecyclerViewId(): Int = R.id.recycler_smart_view
    var mAdapter: BaseRecyAdapter<N>? = null
    protected var mDatasList = ArrayList<N>()
    open fun isSaveDatasToLocal(): Boolean = true


    override fun getContext(): Context {
        return this
    }

    override fun initViews() {
        recycler_view = findViewById(getRecyclerViewId())
        recycler_view?.layoutManager = setLayoutManager()
        recycler_view?.adapter = setAdapter().also { mAdapter = it }
        if (recycler_view?.itemDecorationCount == 0) {
            recycler_view?.addItemDecoration(addItemDecoration())
        }
    }

    /**
     * 为追加数据而设计
     * 同时可以使用baseRecyAdpater fixdatas刷新数据
     */
    open fun appendDatas(list: List<N>) {
        if (isSaveDatasToLocal())
            mDatasList.addAll(list)
        mAdapter?.appendDatas(list)
    }

    /**
     * 为多种类item设计的静态页面数据
     */
//    TODO
    open fun appendDatas() {
        mAdapter?.appendDatas(mDatasList)
        mAdapter?.notifyDataSetChanged()
    }

    /**
     * 重置数据
     */
    fun refreshDatas() {
        mAdapter?.refreshDatas(mDatasList)
    }

    fun clearData() {
        if (isSaveDatasToLocal())
            mDatasList.clear()
        mAdapter?.clearDatas()
    }


}