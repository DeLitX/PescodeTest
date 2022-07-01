package com.delitx.pescodetest.ui.pager

import androidx.lifecycle.ViewModel
import com.delitx.pescodetest.domain.repository.ActionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagerViewModel @Inject constructor(private val repository: ActionsRepository) : ViewModel() {
    val pagesAmount = repository.number
    fun getNotificationIdsByPage(page: Int): List<Int> = repository.getNotificationIdsByPage(page)
}
