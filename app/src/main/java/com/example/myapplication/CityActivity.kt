package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val cityName = intent.getStringExtra("CITY_NAME")
        val cityNameTextView = findViewById<TextView>(R.id.cityNameTextView)
        cityNameTextView.text = cityName

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)

        when (cityName) {
            "Bethlehem" -> {
                button1.text = "Parks"
                button2.text = "Hotels"
                button3.text = "Restaurants"
                button4.text = "Cafes"
                button5.text = "Religious Sites"
                button6.visibility = View.GONE
            }
            "Jericho" -> {
                button1.text = "Water Parks"
                button2.text = "Hotels"
                button3.text = "Restaurants"
                button4.text = "Cafes"
                button5.text = "Religious Sites"
                button6.text = "Special Sites"
                button6.visibility = View.VISIBLE
            }
            "Ramallah" -> {
                button1.text = "Hotels"
                button2.text = "Restaurants"
                button3.text = "Cafes"
                button4.text = "Cinema"
                button5.text = "Activities"
                button6.text = "Pools"
                button6.visibility = View.VISIBLE
            }
            "Hebron" -> {
                button1.text = "Parks"
                button2.text = "Hotels"
                button3.text = "Restaurants"
                button4.text = "Cafes"
                button5.text = "Religious Sites"
                button6.text = "Special Sites"
                button6.visibility = View.VISIBLE
            }
            "Nablus" -> {
                button1.text = "Parks"
                button2.text = "Hotels"
                button3.text = "Restaurants"
                button4.text = "Cafes"
                button5.text = "Religious Sites"
                button6.text = "Special Sites"
                button6.visibility = View.VISIBLE
            }
            else -> {
                button1.text = "Restaurants"
                button2.text = "Hotels"
                button3.text = "Attractions"
                button4.text = "Malls"
                button5.text = "Cafes"
                button6.text = "Parks"
                button6.visibility = View.VISIBLE
            }
        }
    }
}
