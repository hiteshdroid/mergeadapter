package com.merge.adapter.sample.view.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merge.adapter.sample.view.adapter.base.BaseHorizontalListAdapter.HorizontalRecyclerVH
import com.merge.adapter.sample.view.adapter.base.BaseRecyclerViewAdapter.BaseVH

abstract class BaseHorizontalListAdapter<T, K : BaseVH> :
    BaseRecyclerViewAdapter<HorizontalRecyclerVH<T, K>>() {

    private lateinit var parentVH: HorizontalRecyclerVH<T, K>

    class HorizontalRecyclerVH<T, K : BaseVH>(
        parentAdapter: BaseHorizontalListAdapter<T, K>,
        view: RecyclerView) : BaseVH(view) {

        fun setListItems(items: MutableList<T>) {
            adapter.setData(items)
        }

        private var adapter = InternalListAdapter(parentAdapter)

        init {
            view.adapter = InternalListAdapter(parentAdapter)
            view.adapter = adapter
        }


        class InternalListAdapter<T, K : BaseVH>(private val parentAdapter: BaseHorizontalListAdapter<T, K>) :
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
