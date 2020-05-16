package com.valy.baselibrary.baseCommon.basemvp

import android.view.ViewGroup
import android.widget.ImageView
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.valy.baselibrary.R

/**

 * @Auther: valy

 * @datetime: 2020/3/13

 * @desc:

 */
abstract class CommonMVPSmartListActivity<V : CommonView, T : CommonPresenterImpl<V>, N> :
    CommonMVPListActivity<V, T, N>(), CommonView {
    open fun getSmartLayoutId(): Int = R.id.smartlayout
    open fun getSmartEmptyId(): Int = R.id.smart_frame
    open fun getPageDatas(mCurrentPage: Int) {}
    private var isHaveMore: Boolean = true
    var isMore: Boolean = true
    private var mCurrentPage: Int = 1
    private var mSmartRefreshLayout: SmartRefreshLayout? = null
    private var mSmartFrameLayout: ViewGroup? = null
    override fun getLayoutId(): Int = R.layout.recycler_smart_toolbar_layout

    override fun initViews() {
        super.initViews()
        mSmartRefreshLayout = this.findViewById(getSmartLayoutId())
        mSmartRefreshLayout?.setRefreshHeader(WaterDropHeader(mContext))
        mSmartFrameLayout = this.findViewById(getSmartEmptyId())
        showNoData()
        mSmartRefreshLayout?.setOnRefreshListener {
            refresh()
            it.finishRefresh()
        }
        mSmartRefreshLayout?.setOnLoadMoreListener {
            if (isHaveMore) {
                mCurrentPage++
                getPageDatas(mCurrentPage)
                it.finishLoadMore()
            } else {
                it.finishLoadMoreWithNoMoreData()
            }

        }


    }

    open fun refresh() {
        clearData()
        mCurrentPage = 1
        isHaveMore = true
        isMore = true
        mSmartRefreshLayout?.setNoMoreData(false)
        getPageDatas(mCurrentPage)
    }

    var mNoDataIv: ImageView? = null
    fun showNoData() {
        mNoDataIv = ImageView(mContext)
        mNoDataIv?.scaleType = ImageView.ScaleType.CENTER_INSIDE
        mNoDataIv?.setImageDrawable(resources.getDrawable(R.drawable.smart_nodata_icon))
        val linearParams = ViewGroup.MarginLayoutParams(
            ViewGroup.MarginLayoutParams.MATCH_PARENT,
            ViewGroup.MarginLayoutParams.MATCH_PARENT
        )
        mSmartFrameLayout?.addView(mNoDataIv, linearParams)
    }

    fun stopNoData() {
        if (mNoDataIv != null)
            mSmartFrameLayout?.removeView(mNoDataIv)
    }

    override fun initDatas() {
        getPageDatas(mCurrentPage)

    }

    //TODO
    override fun appendDatas() {
        isHaveMore = isMore
        if (mDatasList.isNotEmpty() && mCurrentPage == 1)
            stopNoData()
        if (isHaveMore) {
            super.appendDatas()
            mSmartRefreshLayout?.setNoMoreData(false)
        } else {
            mSmartRefreshLayout?.setNoMoreData(true)
        }
    }

    override fun appendDatas(list: List<N>) {
        if (list.isNotEmpty() && mCurrentPage == 1)
            stopNoData()
        if (isHaveMore) {
            super.appendDatas(list)
            mSmartRefreshLayout?.setNoMoreData(false)
        } else {
            mSmartRefreshLayout?.setNoMoreData(true)
        }
        isHaveMore = list.isNotEmpty() && isMore

    }
}