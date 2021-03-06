package com.delitx.pescodetest.domain.repository

import com.delitx.pescodetest.domain.local.NumberSaver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActionsRepositoryImpl(private val numberSaver: NumberSaver) : ActionsRepository {
    private val _number = MutableStateFlow(1)
    override val number = _number.asStateFlow()
    private val _currentPage: MutableSharedFlow<Int> = MutableSharedFlow()
    override val currentPage: SharedFlow<Int> = _currentPage
    private val notifications = mutableMapOf<Int, MutableList<Int>>()

    var lastNotificationId: Int = 0

    init {
        _number.value = numberSaver.get()
    }

    override fun increaseNumber() {
        _number.value = _number.value + 1
        numberSaver.save(_number.value)
    }

    override fun decreaseNumber() {
        if (_number.value > 0) {
            _number.value = _number.value - 1
            numberSaver.save(_number.value)
        }
    }

    override fun getNewNotificationId(): Int {
        lastNotificationId++
        return lastNotificationId
    }

    override fun setCurrentPage(page: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            _currentPage.emit(page)
        }
    }

    override fun registerNotification(pageNumber: Int, notificationId: Int) {
        if (notifications[pageNumber] == null) {
            notifications[pageNumber] = mutableListOf(notificationId)
        } else {
            notifications[pageNumber]!!.add(notificationId)
        }
    }

    override fun getNotificationIdsByPage(pageNumber: Int): List<Int> {
        return notifications[pageNumber] ?: listOf()
    }
}
