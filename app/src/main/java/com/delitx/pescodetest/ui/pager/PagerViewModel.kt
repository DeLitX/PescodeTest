package com.delitx.pescodetest.ui.pager

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagerViewModel @Inject constructor() : ViewModel() {
    val pagesAmount = 5
}
