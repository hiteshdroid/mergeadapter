package com.merge.adapter.sample.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.merge.adapter.sample.R
import com.merge.adapter.sample.model.data.ListItem
import com.merge.adapter.sample.view.adapter.base.ArrayItemAdapter

class GridListAdapter : ArrayItemAdapter<ListItem, GridListAdapter.GridListAdapterVH>() {
    class GridListAdapterVH(view : View) : BaseVH(view) {
        val title : TextView = view.findViewById(R.id.title)
        val desc : TextView = view.findViewById(R.id.desc)
        val leadImage : ImageView = view.findViewById(R.id.imageView)
    }

    override fun getSpanSize() = 1

    override fun createViewHolder(view: View): GridListAdapterVH {
        return GridListAdapterVH(view)
    }

    override fun getItemLayout() = R.layout.grid_list_item

    override fun bindView(holder: GridListAdapterVH, item: ListItem) {
        holder.title.text = item.title
        holder.desc.text = item.desc
        Glide.with(holder.leadImage.context).load(item.imageUrl).into(holder.leadImage)
    }
}