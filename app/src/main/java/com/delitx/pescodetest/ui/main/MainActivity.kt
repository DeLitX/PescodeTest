package com.delitx.pescodetest.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delitx.pescodetest.R
import com.delitx.pescodetest.ui.pager.PagerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PagerFragment())
                .commitNow()
        }
    }
}
