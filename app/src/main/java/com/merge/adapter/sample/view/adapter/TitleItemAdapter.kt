package com.merge.adapter.sample.view.adapter

import android.view.View
import android.widget.TextView
import com.merge.adapter.sample.R
import com.merge.adapter.sample.model.data.TitleItem
import com.merge.adapter.sample.view.adapter.base.SingleItemAdapter

class TitleItemAdapter : SingleItemAdapter<TitleItem, TitleItemAdapter.TitleItemAdapterVH>() {
    class TitleItemAdapterVH(view : View) : BaseVH(view){
        private val header = view.findViewById<TextView>(R.id.dummy_title)
        fun bindData(title: String) {
            header.text = title
        }
    }

    override fun createViewHolder(view: View): TitleItemAdapterVH {
        return TitleItemAdapterVH(view)
    }

    override fun getItemLayout() = R.layout.dummy_title

    override fun bindView(holder: TitleItemAdapterVH, item: TitleItem) {
        holder.bindData(item.title)
    }

    override fun getSpanSize() = 2
}
