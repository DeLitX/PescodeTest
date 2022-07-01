package com.delitx.pescodetest.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ActionsRepositoryImpl() : ActionsRepository {
    private val _number = MutableStateFlow(1)
    override val number = _number.asStateFlow()
    private val notifications = mutableMapOf<Int, MutableList<Int>>()

    var lastNotificationId: Int = 0

    override fun increaseNumber() {
        _number.value = _number.value + 1
    }

    override fun decreaseNumber() {
        if (_number.value > 0) {
            _number.value = _number.value - 1
        }
    }

    override fun getNewNotificationId(): Int {
        lastNotificationId++
        return lastNotificationId
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
