package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager1: ViewPager2
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPager3: ViewPager2
    private lateinit var viewPager4: ViewPager2
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Spinner setup
        val spinner = findViewById<Spinner>(R.id.spinner3)
        val cities = resources.getStringArray(R.array.cityArray).toMutableList()
        cities.add(0, "City")

        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent) as TextView
                view.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                return view
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    view.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
                }
                return view
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    val selectedCity = parent?.getItemAtPosition(position).toString()
                    val intent = Intent(this@MainActivity, CityActivity::class.java)
                    intent.putExtra("CITY_NAME", selectedCity)
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // ViewPager setup
        viewPager1 = findViewById(R.id.viewPager1)
        viewPager2 = findViewById(R.id.viewPager2)
        viewPager3 = findViewById(R.id.viewPager3)
        viewPager4 = findViewById(R.id.viewPager4)

        val imageList1 = listOf(
            ImageData(R.drawable.church, "Bethlehem\nChurch of the Nativity"),
            ImageData(R.drawable.heb, "Hebron\nGlass & Ceramics Shops"),
            ImageData(R.drawable.mill, "Ramallah\nMillennium Hotel")
        )

        val imageList2 = listOf(
            ImageData(R.drawable.tower, "Nablus\nClock Tower"),
            ImageData(R.drawable.pool, "Jericho\nElisha Spring Fountain")
        )

        val imageList3 = listOf(
            ImageData(R.drawable.afteen, "Bethlehem\nAfteen Resturant"),
            ImageData(R.drawable.sama, "Nablus\nNational park"),
            ImageData(R.drawable.lazaward, "Ramallah\nlazaward restaurant")
        )

        val imageList4 = listOf(
            ImageData(R.drawable.jasem, "Bethlehem\nPalace Hotel JASEER"),
            ImageData(R.drawable.hesham, "Nablus\nHesham Palace"),
            ImageData(R.drawable.tal, "Ramallah\nTeleferik")
        )

        viewPager1.adapter = ImageAdapter(imageList1)
        viewPager2.adapter = ImageAdapter(imageList2)
        viewPager3.adapter = ImageAdapter(imageList3)
        viewPager4.adapter = ImageAdapter(imageList4)

        val runnable1 = object : Runnable {
            override fun run() {
                viewPager1.currentItem = (viewPager1.currentItem + 1) % imageList1.size
                handler.postDelayed(this, 5000)
            }
        }

        val runnable2 = object : Runnable {
            override fun run() {
                viewPager2.currentItem = (viewPager2.currentItem + 1) % imageList2.size
                handler.postDelayed(this, 5000)
            }
        }

        val runnable3 = object : Runnable {
            override fun run() {
                viewPager3.currentItem = (viewPager3.currentItem + 1) % imageList3.size
                handler.postDelayed(this, 5000)
            }
        }

        val runnable4 = object : Runnable {
            override fun run() {
                viewPager4.currentItem = (viewPager4.currentItem + 1) % imageList4.size
                handler.postDelayed(this, 5000)
            }
        }

        handler.post(runnable1)
        handler.post(runnable2)
        handler.post(runnable3)
        handler.post(runnable4)
    }
}
