package com.merge.adapter.sample.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merge.adapter.sample.R
import com.merge.adapter.sample.model.data.ListItem
import com.merge.adapter.sample.view.adapter.BannerItemAdapter
import com.merge.adapter.sample.view.adapter.GridListAdapter
import com.merge.adapter.sample.view.adapter.HorizontalListAdapter
import com.merge.adapter.sample.view.adapter.VerticalListAdapter
import com.merge.adapter.sample.view.base.CustomSpanSizeLookup
import com.merge.adapter.sample.viewmodel.BaseTemplate
import com.merge.adapter.sample.viewmodel.BaseTemplate.*
import com.merge.adapter.sample.viewmodel.ListFragmentViewModel
import com.merge.adapter.sample.viewmodel.ResponseStatus


class ListFragment : BaseFragment<ListFragment.ListViewHolder>() {
    private val listViewModel: ListFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()
    }

    private fun observeData() {
        val dataObserver = Observer<ResponseStatus> { responseStatus ->
            when (responseStatus) {
                is ResponseStatus.Loading -> showProgressBar()
                is ResponseStatus.Error -> showError(responseStatus.message)
                is ResponseStatus.Success -> showList(responseStatus.list)
            }
        }

        listViewModel.responseLiveData.observe(this, dataObserver)
    }

    private fun showList(list: MutableList<BaseTemplate>) {
        hideError()
        hideProgressBar()
        populateAdapter(list)
    }

    private fun populateAdapter(list: MutableList<BaseTemplate>) {
        list.forEach {
            when (it) {
                is HorizontalList -> createHorizontalListAdapter(it.itemList)
                is VerticalList -> createVerticalListAdapter(it.itemList)
                is GridList -> createGridListAdapter(it.itemList)
                is SingleItem -> createSingleItemAdapter(it.item)
            }
        }
        viewHolder?.apply {
            adapter.notifyDataSetChanged()
        }
    }

    private fun createSingleItemAdapter(item: ListItem) {
        viewHolder?.apply {
            val singleItemAdapter =
                BannerItemAdapter()
            singleItemAdapter.setItem(item)
            adapter.addAdapter(singleItemAdapter)
        }
    }

    private fun createGridListAdapter(itemList: MutableList<ListItem>) {
        viewHolder?.apply {
            viewHolder?.apply {
                val gridListAdapter = GridListAdapter()
                gridListAdapter.setData(itemList)
                adapter.addAdapter(gridListAdapter)
            }
        }
    }

    private fun createVerticalListAdapter(itemList: MutableList<ListItem>) {
        viewHolder?.apply {
            val verticalListAdapter = VerticalListAdapter()
            verticalListAdapter.setData(itemList)
            adapter.addAdapter(verticalListAdapter)
        }
    }

    private fun createHorizontalListAdapter(itemList: MutableList<ListItem>) {
        viewHolder?.apply {
            viewHolder?.apply {
                val horizontalListAdapter = HorizontalListAdapter(itemList)
                adapter.addAdapter(horizontalListAdapter)
            }
        }
    }

    class ListViewHolder(view: View) : BaseViewHolder(view) {
        private val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        internal val adapter = MergeAdapter()

        init {
            recyclerView.adapter = adapter
            val layoutManager = GridLayoutManager(
                view.context,
                2
            )
            layoutManager.spanSizeLookup = CustomSpanSizeLookup(adapter)
            recyclerView.layoutManager = layoutManager
        }
    }


    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    override fun onCreateViewHolder(view: View): ListViewHolder {
        return ListViewHolder(view)
    }

    override fun getFragmentLayout() = R.layout.list_layout

    override fun onViewHolderCreated(viewHolder: ListViewHolder) {
        viewHolder.progressBar.visibility = View.GONE
        viewHolder.errorView.visibility = View.GONE
    }
}