package com.delitx.pescodetest.domain.local

interface NumberSaver {
    fun save(number: Int)
    fun get(): Int
}
