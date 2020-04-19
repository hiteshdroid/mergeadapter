package com.merge.adapter.sample.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.merge.adapter.sample.R
import com.merge.adapter.sample.model.data.ListItem
import com.merge.adapter.sample.view.adapter.base.HorizontalScrollListItemAdapter

class HorizontalListAdapter(private val itemList: MutableList<ListItem>) :
    HorizontalScrollListItemAdapter<ListItem, HorizontalListAdapter.HorizontalListItemAdapterVH>() {
    class HorizontalListItemAdapterVH(view: View) : BaseVH(view) {
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.desc)
        val leadImage: ImageView = view.findViewById(R.id.imageView)
    }

    override fun createViewHolder(view: View): HorizontalListItemAdapterVH {
        return HorizontalListItemAdapterVH(view)
    }

    override fun getItemLayout() = R.layout.horizontal_list_item

    override fun bindView(holder: HorizontalListItemAdapterVH, item: ListItem) {
        holder.title.text = item.title
        holder.desc.text = item.desc
        Glide.with(holder.leadImage.context).load(item.imageUrl).into(holder.leadImage)
    }

    override fun getItems() = itemList
}