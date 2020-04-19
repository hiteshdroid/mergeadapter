package com.merge.adapter.sample.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.merge.adapter.sample.R
import com.merge.adapter.sample.model.data.Screen
import com.merge.adapter.sample.model.ScreenType
import com.merge.adapter.sample.viewmodel.ActivityViewModel

class MainActivity : AppCompatActivity() {

    private val activityViewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeNextPageToLoad()
        loadHomeScreen()
    }

    private fun loadHomeScreen() {
        activityViewModel.loadScreen(
            Screen(
                ScreenType.LIST,
                null
            )
        )
    }

    private fun observeNextPageToLoad() {
        val screenObserver = Observer<Screen> { screen ->
            loadScreen(screen)
        }

        activityViewModel.screenLiveData.observe(this, screenObserver)
    }

    private fun loadScreen(screen: Screen) {
        when (screen.screenType) {
            ScreenType.LIST -> swapFragment(ListFragment.newInstance(), screen.bundle)
            ScreenType.DETAIL -> swapFragment(DetailFragment.newInstance(), screen.bundle)
        }
    }

    private fun swapFragment(fragment: Fragment, bundle: Bundle?) {
        bundle?.apply {
            fragment.arguments = bundle
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
    }
}
