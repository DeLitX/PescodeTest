package com.delitx.pescodetest.domain.local

import android.content.Context

class SharedPreferencesSaver(private val context: Context) : NumberSaver {
    companion object {
        const val PREFERENCES_CODE = "shared_preferences"
        const val NUMBER_CODE = "shared_preferences"
    }

    override fun save(number: Int) {
        val preferences = context.getSharedPreferences(PREFERENCES_CODE, Context.MODE_PRIVATE)
        preferences.edit().putInt(NUMBER_CODE, number).apply()
    }

    override fun get(): Int {
        val preferences = context.getSharedPreferences(PREFERENCES_CODE, Context.MODE_PRIVATE)
        return preferences.getInt(NUMBER_CODE, 1)
    }
}
