package com.delitx.pescodetest.ui.main

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.delitx.pescodetest.ui.action.ActionFragment

class ViewPagerAdapter(
    activity: FragmentActivity,
    private val itemCountGetter: ItemCountGetter
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return itemCountGetter.getItemCount()
    }

    override fun createFragment(position: Int): Fragment {
        return ActionFragment().apply {
            arguments = bundleOf(ActionFragment.NUMBER_KEY to position + 1)
        }
    }

    fun interface ItemCountGetter {
        fun getItemCount(): Int
    }
}
