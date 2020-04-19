package com.merge.adapter.sample.view

import android.view.View
import com.merge.adapter.sample.R

class DetailFragment : BaseFragment<DetailFragment.DetailFragmentViewHolder>() {
    class DetailFragmentViewHolder(view : View) : BaseViewHolder(view){

    }

    override fun onCreateViewHolder(view: View): DetailFragmentViewHolder {
        return DetailFragmentViewHolder(view)
    }

    override fun getFragmentLayout() = R.layout.detail_layout

    override fun onViewHolderCreated(viewHolder: DetailFragmentViewHolder) {

    }

    companion object {
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }
}