package com.merge.adapter.sample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.merge.adapter.sample.R

abstract class BaseFragment<T : BaseFragment.BaseViewHolder> : Fragment() {
    var viewHolder: T? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getFragmentLayout(), container, false)
        viewHolder = onCreateViewHolder(view)
        return view
    }

    abstract fun onCreateViewHolder(view: View): T

    abstract fun getFragmentLayout() : Int

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewHolderCreated(viewHolder!!)
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun onViewHolderCreated(viewHolder: T)

    open class BaseViewHolder(view: View) {
        internal val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        internal val errorView: TextView = view.findViewById(R.id.errorView)
    }

    override fun onDestroyView() {
        viewHolder = null
        super.onDestroyView()
    }

    internal fun showError(message: String) {
        viewHolder?.apply {
            errorView.visibility = View.VISIBLE
            errorView.text = message
        }
    }

    internal fun hideError() {
        viewHolder?.apply {
            errorView.visibility = View.GONE
        }
    }

    internal fun showProgressBar() {
        viewHolder?.apply {
            progressBar.visibility = View.VISIBLE
        }
    }

    internal fun hideProgressBar() {
        viewHolder?.apply {
            progressBar.visibility = View.GONE
        }
    }
}