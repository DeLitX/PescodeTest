package com.delitx.pescodetest.ui.pager

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.delitx.pescodetest.R
import com.delitx.pescodetest.ui.main.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PagerFragment : Fragment() {
    private val viewModel: PagerViewModel by viewModels()
    private var currentPagerSize = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pager, container, false)
        val getter = ViewPagerAdapter.ItemCountGetter {
            val number = viewModel.pagesAmount.value
            number
        }
        val adapter = ViewPagerAdapter(requireActivity(), getter)
        val viewPager: ViewPager2 = view.findViewById(R.id.pager)
        viewPager.adapter = adapter
        lifecycleScope.launchWhenCreated {
            viewModel.currentPage.collect {
                viewPager.currentItem = it - 1
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.pagesAmount.collect {
                if (viewPager.currentItem > it - 1) {
                    withContext(Dispatchers.Main) {
                        viewPager.currentItem = it - 1
                    }
                }
                while (currentPagerSize < it) {
                    adapter.notifyItemInserted(currentPagerSize)
                    currentPagerSize++
                }
                while (currentPagerSize > it) {
                    adapter.notifyItemRemoved(currentPagerSize - 1)
                    removeNotificationsForPage(currentPagerSize)
                    currentPagerSize--
                }
            }
        }
        return view
    }

    private fun removeNotificationsForPage(page: Int) {
        val notificationManager =
            requireActivity().applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val ids = viewModel.getNotificationIdsByPage(page)
        for (id in ids) {
            notificationManager.cancel(id)
        }
    }
}
