package com.valy.baselibrary.baseCommon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valy.baselibrary.R
import java.util.ArrayList

/**
 * @Auther: valy
 * @datetime: 2020/3/6
 * @desc:
 */
abstract class BaseRecyAdapter<VH> : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected val VIEW_TYPE_CONTENT: Int = 1
    protected val VIEW_TYPE_BOTTOM: Int = 2
    protected val VIEW_TYPE_TOOLBAR: Int = 3
    protected val VIEW_TYPE_CONTENT_TWO: Int = 4
    open var mDatas = ArrayList<VH>()
    protected val context: Context?
    protected val inflater: LayoutInflater
    open fun fixData(mMAp: Map<String, String>) {}


    constructor(context: Context?) {
        this.context = context
        this.inflater = LayoutInflater.from(context)
    }


    open fun clearDatas() {
        mDatas.clear()
        notifyDataSetChanged()
    }

    open fun appendDatas(vh: List<VH>) {
        if (vh.isNotEmpty()) {
            mDatas.addAll(vh)
            notifyDataSetChanged()

        }
    }

    open fun fixDatas(mMAp: Map<String, String>) {
        fixData(mMAp)
    }

    open fun fixDatas(index: Int) {
        if (mDatas[index] != null) {
            mDatas.removeAt(index)
            notifyItemChanged(index)
        }

    }

    open fun fixDatas(dddd: VH, index: Int) {
        if (mDatas[index] != null) {
            mDatas[index] = dddd
            notifyItemChanged(index)
        }

    }

    open fun refreshDatas(vh: List<VH>) {
        if (vh.isNotEmpty()) {
            mDatas.clear()
            mDatas = vh as ArrayList<VH>
            notifyDataSetChanged()

        }
    }

    interface OnClickListenerInterface {
        fun click(position: Int, v: View)
        fun onLongClick(position: Int, v: View)
    }

    interface ToolBarOnClickInterface {
        fun onBack(v: View)
        fun onRightClick(s: String, v: View)
    }

    open var toolBarClickInterface: ToolBarOnClickInterface? = null
    open var clickListenerInterface: OnClickListenerInterface? = null

    override fun getItemCount(): Int = mDatas.size


    class ToolBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_back: ImageView? = null
        var tv_title: TextView? = null
        var tv_right_title: TextView? = null

        init {
            iv_back = itemView.findViewById(R.id.iv_back)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_right_title = itemView.findViewById(R.id.tv_right_title)
        }
    }


}