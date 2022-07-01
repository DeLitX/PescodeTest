package com.delitx.pescodetest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.delitx.pescodetest.R
import com.delitx.pescodetest.ui.pager.PagerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        const val SELECTED_PAGE_EXTRA = "selected_page"
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            val page = it.getIntExtra(SELECTED_PAGE_EXTRA, 1)
            viewModel.setCurrentPage(page)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val page = intent.getIntExtra(SELECTED_PAGE_EXTRA, 1)
        viewModel.setCurrentPage(page)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PagerFragment())
                .commitNow()
        }
    }
}
