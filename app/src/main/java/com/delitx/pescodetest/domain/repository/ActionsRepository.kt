package com.delitx.pescodetest.domain.repository

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ActionsRepository {
    val number: StateFlow<Int>
    val currentPage: SharedFlow<Int>
    fun increaseNumber()
    fun decreaseNumber()
    fun getNewNotificationId(): Int
    fun setCurrentPage(page: Int)
    fun registerNotification(pageNumber: Int, notificationId: Int)
    fun getNotificationIdsByPage(pageNumber: Int): List<Int>
}
