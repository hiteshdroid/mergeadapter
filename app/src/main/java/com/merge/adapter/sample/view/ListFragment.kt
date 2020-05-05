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
                is HorizontalList -> populateHorizontalList(it.itemList)
                is VerticalList -> populateVerticalListAdapter(it.itemList)
                is GridList -> populateGridListAdapter(it.itemList)
                is SingleItem -> populateSingleItemAdapter(it.item)
            }
        }
    }

    private fun populateSingleItemAdapter(item: ListItem) {
        viewHolder?.apply {
            bannerItemAdapter.setItem(item)
        }
    }

    private fun populateGridListAdapter(itemList: MutableList<ListItem>) {
        viewHolder?.apply {
            viewHolder?.apply {
                gridListAdapter.setData(itemList)
            }
        }
    }

    private fun populateVerticalListAdapter(itemList: MutableList<ListItem>) {
        viewHolder?.apply {
            verticalListAdapter.setData(itemList)
        }
    }

    private fun populateHorizontalList(itemList: MutableList<ListItem>) {
        viewHolder?.apply {
            viewHolder?.apply {
                horizontalListAdapter.setItems(itemList)
            }
        }
    }

    inner class ListViewHolder(view: View) : BaseViewHolder(view) {
        private val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        internal val adapter = MergeAdapter()
        internal val horizontalListAdapter = HorizontalListAdapter()
        internal val verticalListAdapter = VerticalListAdapter()
        internal val bannerItemAdapter = BannerItemAdapter()
        internal val gridListAdapter = GridListAdapter()

        init {
            adapter.addAdapter(bannerItemAdapter)
            adapter.addAdapter(horizontalListAdapter)
            adapter.addAdapter(verticalListAdapter)
            adapter.addAdapter(gridListAdapter)

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