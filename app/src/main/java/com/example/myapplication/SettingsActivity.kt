package com.example.myapplication

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.applyTheme(this)
        setContentView(R.layout.activity_settings)

        val darkModeSwitch = findViewById<Switch>(R.id.darkModeSwitch)

        darkModeSwitch.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ThemeManager.setTheme(this, AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                ThemeManager.setTheme(this, AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}