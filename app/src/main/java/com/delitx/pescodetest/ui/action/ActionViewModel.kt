package com.delitx.pescodetest.ui.action

import androidx.lifecycle.ViewModel
import com.delitx.pescodetest.domain.repository.ActionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActionViewModel @Inject constructor(private val repository: ActionsRepository) : ViewModel() {
    var currentFragmentNumber: Int = 1
    fun createNewPage() {
        repository.increaseNumber()
    }

    fun removePage() {
        repository.decreaseNumber()
    }

    fun notifyMakeNotification() {
        repository.notifyMakeNotification(currentFragmentNumber)
    }
}
