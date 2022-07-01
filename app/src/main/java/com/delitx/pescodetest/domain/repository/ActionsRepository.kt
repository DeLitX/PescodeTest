package com.delitx.pescodetest.domain.repository

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ActionsRepository {
    val notificationAction: SharedFlow<Int>
    val number: StateFlow<Int>
    fun increaseNumber()
    fun decreaseNumber()
    fun registerNotification(pageNumber: Int, notificationId: Int)
    fun notifyMakeNotification(pageNumber: Int)
    fun getNotificationIdsByPage(pageNumber: Int): List<Int>
}
