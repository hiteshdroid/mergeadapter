package com.merge.adapter.sample.view.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merge.adapter.sample.R
import com.merge.adapter.sample.view.adapter.base.BaseRecyclerViewAdapter.BaseVH
import com.merge.adapter.sample.view.adapter.base.HorizontalScrollListItemAdapter.HorizontalRecyclerVH

abstract class HorizontalScrollListItemAdapter<T, K : BaseVH> :
    BaseRecyclerViewAdapter<HorizontalRecyclerVH<T, K>>() {

    private lateinit var parentVH: HorizontalRecyclerVH<T, K>

    class HorizontalRecyclerVH<T, K : BaseVH>(
        parentAdapter: HorizontalScrollListItemAdapter<T, K>,
        view: View) : BaseVH(view) {

        fun setListItems(items: MutableList<T>) {
            adapter.setData(items)
        }

        private val recyclerView: RecyclerView = view.findViewById(R.id.horizontal_recycler_view_id)
        private var adapter = InternalListAdapter(parentAdapter)

        init {
            recyclerView.adapter = InternalListAdapter(parentAdapter)
            recyclerView.adapter = adapter
        }


        class InternalListAdapter<T, K : BaseVH>(private val parentAdapter: HorizontalScrollListItemAdapter<T, K>) :
            RecyclerView.Adapter<K>() {
            private var list: MutableList<T>? = null

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K {
                val view = LayoutInflater.from(parent.context)
                    .inflate(parentAdapter.getItemLayout(), parent, false)
                return parentAdapter.createViewHolder(view)
            }

            override fun getItemCount(): Int {
                return list?.size ?: 0
            }

            fun setData(data: MutableList<T>) {
                this.list = data
                notifyDataSetChanged()
            }

            override fun onBindViewHolder(holder: K, position: Int) {
                parentAdapter.bindView(holder, list!![position])
            }

        }
    }

    override fun getSpanSize() = 2

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalRecyclerVH<T, K> {
        val recyclerView = RecyclerView(parent.context)
        recyclerView.id = R.id.horizontal_recycler_view_id
        recyclerView.layoutManager =
            LinearLayoutManager(parent.context, RecyclerView.HORIZONTAL, false)
        return createRecyclerViewHolder(recyclerView)
    }

    private fun createRecyclerViewHolder(
        recyclerView: RecyclerView
    ): HorizontalRecyclerVH<T, K> {
        this.parentVH = HorizontalRecyclerVH(
            this,
            recyclerView)
        return parentVH
    }


    abstract fun createViewHolder(view: View): K

    abstract fun getItemLayout(): Int

    final override fun getItemCount() = 1

    abstract fun bindView(holder: K, item: T)

    abstract fun getItems() : MutableList<T>

    final override fun onBindViewHolder(holder: HorizontalRecyclerVH<T, K>, position: Int) {
        setListItems(getItems())
    }

    private fun setListItems(items: MutableList<T>) {
        parentVH.setListItems(items)
    }
}
