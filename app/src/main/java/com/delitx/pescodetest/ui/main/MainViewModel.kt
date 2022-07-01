package com.delitx.pescodetest.ui.main

import androidx.lifecycle.ViewModel
import com.delitx.pescodetest.domain.repository.ActionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ActionsRepository) : ViewModel() {
    fun setCurrentPage(page: Int) {
        repository.setCurrentPage(page)
    }
}
