package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ZoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.applyTheme(this)
        setContentView(R.layout.activity_zoom)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val imageRes = intent.getIntExtra("IMAGE_RES", 0)
        val zoomedImageView = findViewById<ImageView>(R.id.zoomedImageView)
        if (imageRes != 0) {
            zoomedImageView.setImageResource(imageRes)
        }
    }
}
