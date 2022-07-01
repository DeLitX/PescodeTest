package com.delitx.pescodetest.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface ActionsRepository {
    val number: StateFlow<Int>
    fun increaseNumber()
    fun decreaseNumber()
    fun getNewNotificationId(): Int
    fun registerNotification(pageNumber: Int, notificationId: Int)
    fun getNotificationIdsByPage(pageNumber: Int): List<Int>
}
