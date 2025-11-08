package com.example.myapplication

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private val scrollRunnables = mutableListOf<Runnable>()
    private var userId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.applyTheme(this)
        setContentView(R.layout.activity_main)

        userId = intent.getLongExtra("USER_ID", -1)

        val settingsButton = findViewById<Button>(R.id.settingsButton)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        setupSpinner()
        setupViewPagers()
    }

    override fun onResume() {
        super.onResume()
        startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        stopAutoScroll()
    }

    private fun setupSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner3)
        val cities = resources.getStringArray(R.array.cityArray).toMutableList()
        cities.add(0, "City")

        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent) as TextView
                val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                    view.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                } else {
                    view.setTextColor(ContextCompat.getColor(context, android.R.color.black))
                }
                return view
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView
                val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                    if (position == 0) {
                        view.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
                    } else {
                        view.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    }
                } else {
                    if (position == 0) {
                        view.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
                    } else {
                        view.setTextColor(ContextCompat.getColor(context, android.R.color.black))
                    }
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
    }

    private fun setupViewPagers() {
        val viewPagerIds = listOf(R.id.viewPager1, R.id.viewPager2, R.id.viewPager3, R.id.viewPager4)
        val imageLists = listOf(
            listOf(
                ImageData(R.drawable.church, "Bethlehem\nChurch of the Nativity"),
                ImageData(R.drawable.heb, "Hebron\nGlass & Ceramics Shops"),
                ImageData(R.drawable.mill, "Ramallah\nMillennium Hotel")
            ),
            listOf(
                ImageData(R.drawable.tower, "Nablus\nClock Tower"),
                ImageData(R.drawable.pool, "Jericho\nElisha Spring Fountain")
            ),
            listOf(
                ImageData(R.drawable.afteen, "Bethlehem\nAfteen Resturant"),
                ImageData(R.drawable.sama, "Nablus\nNational park"),
                ImageData(R.drawable.lazaward, "Ramallah\nlazaward restaurant")
            ),
            listOf(
                ImageData(R.drawable.jasem, "Bethlehem\nPalace Hotel JASEER"),
                ImageData(R.drawable.hesham, "Nablus\nHesham Palace"),
                ImageData(R.drawable.tal, "Ramallah\nTeleferik")
            )
        )

        viewPagerIds.forEachIndexed { index, id ->
            val viewPager = findViewById<ViewPager2>(id)
            val imageList = imageLists.getOrNull(index) ?: return@forEachIndexed
            viewPager.adapter = ImageAdapter(imageList)

            val runnable = object : Runnable {
                override fun run() {
                    viewPager.currentItem = (viewPager.currentItem + 1) % imageList.size
                    handler.postDelayed(this, 5000)
                }
            }
            scrollRunnables.add(runnable)
        }
    }

    private fun startAutoScroll() {
        scrollRunnables.forEach { handler.postDelayed(it, 5000) }
    }

    private fun stopAutoScroll() {
        scrollRunnables.forEach { handler.removeCallbacks(it) }
    }
}
