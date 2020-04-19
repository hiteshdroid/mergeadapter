package com.merge.adapter.sample.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.merge.adapter.sample.R
import com.merge.adapter.sample.model.data.ListItem
import com.merge.adapter.sample.view.adapter.BannerItemAdapter.BannerItemAdapterVH
import com.merge.adapter.sample.view.adapter.base.SingleItemAdapter

class BannerItemAdapter : SingleItemAdapter<ListItem, BannerItemAdapterVH>() {
    override fun createViewHolder(view: View) : BannerItemAdapterVH {
        return BannerItemAdapterVH(view)
    }

    class BannerItemAdapterVH(view: View) : BaseVH(view) {
        val bannerText : TextView  = view.findViewById(R.id.title)
        val bannerImage : ImageView = view.findViewById(R.id.imageView)
    }

    override fun getItemLayout() = R.layout.banner_item

    override fun bindView(holder: BannerItemAdapterVH, item: ListItem) {
        holder.bannerText.text = item.title
        Glide.with(holder.bannerImage.context).load(item.imageUrl).into(holder.bannerImage)
    }

    override fun getSpanSize() = 2
}