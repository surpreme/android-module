package com.valy.newlibrary.news

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.valy.baselibrary.adpter.sticky.PowerGroupListener
import com.valy.baselibrary.adpter.sticky.PowerfulStickyDecoration
import com.valy.baselibrary.baseCommon.AccountConstant
import com.valy.baselibrary.baseCommon.ArouterConstant
import com.valy.baselibrary.baseCommon.BaseRecyAdapter
import com.valy.baselibrary.baseCommon.basemvp.CommonMVPSmartListActivity
import com.valy.baselibrary.utils.screen.ScreenSizeUtils
import com.valy.baselibrary.utils.text.ToastUtils
import com.valy.baselibrary.utils.view.SingleClick
import com.valy.newlibrary.R

/**

 * @Auther: valy

 * @datetime: 2020/5/17

 * @desc:

 */
@Route(path = ArouterConstant.NewsActivity)
class NewsActivity : CommonMVPSmartListActivity<NewsContract.View, NewsPresenter, NewsBean>(),
    NewsContract.View {
    override fun setAdapter(): BaseRecyAdapter<NewsBean> = NewsRecyAdpter(mContext).apply {
        this.clickListenerInterface = object : BaseRecyAdapter.OnClickListenerInterface {
            override fun click(position: Int, v: View) {
                mAdapter?.fixDatas(NewsBean(AccountConstant.KEY, "", "", ""), position)

            }

            override fun onLongClick(position: Int, v: View) {
                ToastUtils.showSnakbar(v, "您确定要删除吗？", object : SingleClick() {
                    override fun onSingleClick(v: View?) {
                        mAdapter?.fixDatas(position)
                    }

                })

            }

        }
    }

    override fun initViews() {
        super.initViews()
        setStatusBar(0)
        initToolBar("列表", getColor(R.color.white))
    }

    override fun addItemDecoration(): RecyclerView.ItemDecoration {
        val mGroupListener = object : PowerGroupListener {
            override fun getGroupName(position: Int): String = mDatasList[position].title

            @SuppressLint("InflateParams")
            override fun getGroupView(position: Int): View {
                val view: View = layoutInflater.inflate(R.layout.text_layout, null, false)
                val textView: TextView = view.findViewById<TextView>(R.id.textView)
                textView.text = mDatasList[position].title
                textView.gravity = Gravity.CENTER_VERTICAL
                textView.textSize = 19f
                if (mDatasList[position].title.isEmpty()) {
                    textView.height = 0
                }
                textView.setPadding(ScreenSizeUtils.dip2px(mContext, 15f), 0, 0, 0)
                return view
            }
        }
        return PowerfulStickyDecoration.Builder
            .init(mGroupListener)
            .build()
    }


    override fun isSaveDatasToLocal(): Boolean = true
    override fun getPageDatas(mCurrentPage: Int) {
        isMore = mCurrentPage != 3
        val datasListS = mutableListOf<NewsBean>()
        if (mCurrentPage == 1 || mCurrentPage == 2 || mCurrentPage == 3) {
            for (index in 0 until 20) {
                val mNewsBean = NewsBean("$index", "", "", if (index % 5 != 0) "" else "$index")
                datasListS.add(mNewsBean)
            }
        }
        appendDatas(datasListS)

    }
}