package com.delitx.pescodetest.domain.repository

import kotlinx.coroutines.flow.*

class ActionsRepositoryImpl() : ActionsRepository {
    private val _notificationAction = MutableSharedFlow<Int>()
    override val notificationAction: SharedFlow<Int> = _notificationAction.asSharedFlow()

    private val _number = MutableStateFlow(1)
    override val number = _number.asStateFlow()
    private val notifications = mutableMapOf<Int, MutableList<Int>>()

    override fun increaseNumber() {
        _number.value = _number.value + 1
    }

    override fun decreaseNumber() {
        if (_number.value > 0) {
            _number.value = _number.value - 1
        }
    }

    override fun registerNotification(pageNumber: Int, notificationId: Int) {
        if (notifications[pageNumber] == null) {
            notifications[pageNumber] = mutableListOf(notificationId)
        } else {
            notifications[pageNumber]!!.add(notificationId)
        }
    }

    override fun notifyMakeNotification(pageNumber: Int) {
    }

    override fun getNotificationIdsByPage(pageNumber: Int): List<Int> {
        return notifications[pageNumber] ?: listOf()
    }
}
