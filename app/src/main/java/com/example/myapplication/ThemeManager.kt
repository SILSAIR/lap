package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

object ThemeManager {
    private const val PREF_NAME = "ThemePrefs"
    private const val KEY_THEME = "theme"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun applyTheme(context: Context) {
        val theme = getPreferences(context).getString(KEY_THEME, AppCompatDelegate.MODE_NIGHT_NO.toString())?.toInt() ?: AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(theme)
    }

    fun setTheme(context: Context, theme: Int) {
        getPreferences(context).edit {
            putString(KEY_THEME, theme.toString())
        }
        AppCompatDelegate.setDefaultNightMode(theme)
    }
}