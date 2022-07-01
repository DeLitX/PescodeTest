package com.delitx.pescodetest.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.delitx.pescodetest.R
import com.delitx.pescodetest.ui.main.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagerFragment : Fragment() {
    private val viewModel: PagerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pager, container, false)
        val getter = ViewPagerAdapter.ItemCountGetter {
            viewModel.pagesAmount
        }
        val adapter = ViewPagerAdapter(requireActivity(), getter)
        val viewPager: ViewPager2 = view.findViewById(R.id.pager)
        viewPager.adapter = adapter
        return view
    }
}
