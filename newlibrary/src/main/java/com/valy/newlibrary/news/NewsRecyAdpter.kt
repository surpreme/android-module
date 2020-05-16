package com.valy.newlibrary.news

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valy.baselibrary.baseCommon.BaseRecyAdapter
import com.valy.baselibrary.utils.view.SingleClick
import com.valy.newlibrary.R

/**

 * @Auther: valy

 * @datetime: 2020/5/17

 * @desc:

 */
class NewsRecyAdpter(mContext: Context) : BaseRecyAdapter<NewsBean>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder =
                ContentViewHolder(inflater.inflate(R.layout.text_layout, parent, false))
            VIEW_TYPE_TOOLBAR -> viewHolder =
                ToolBarViewHolder(inflater.inflate(R.layout.toolbar_layout, parent, false))
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            VIEW_TYPE_TOOLBAR -> {
                (holder as ToolBarViewHolder).tv_title?.text = mDatas[position].toolTitle
                holder.itemView.setBackgroundColor(Color.WHITE)
                holder.iv_back?.setOnClickListener {
                    if (toolBarClickInterface != null) toolBarClickInterface?.onBack(it)
                }

            }
            VIEW_TYPE_CONTENT -> {
                (holder as ContentViewHolder).textView?.text = mDatas[position].s
                holder.itemView.setBackgroundColor(Color.WHITE)
                holder.itemView.setOnClickListener(object : SingleClick() {
                    override fun onSingleClick(v: View) {
                        clickListenerInterface?.click(position, v)
                    }

                })
                holder.itemView.setOnLongClickListener(object :View.OnLongClickListener{
                    override fun onLongClick(v: View): Boolean {
                        clickListenerInterface?.onLongClick(position, v)

                        return true
                    }

                })

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position].toolRightTitle.isNotEmpty()) VIEW_TYPE_TOOLBAR else VIEW_TYPE_CONTENT
    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null


        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }
}