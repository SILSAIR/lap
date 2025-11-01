package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CityActivity : AppCompatActivity() {

    private lateinit var detailsRecyclerView: RecyclerView
    private lateinit var placeAdapter: PlaceAdapter

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

        detailsRecyclerView = findViewById(R.id.detailsRecyclerView)
        detailsRecyclerView.layoutManager = LinearLayoutManager(this)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)

        val buttons = listOf(button1, button2, button3, button4, button5, button6)

        val categories = when (cityName) {
            "Bethlehem" -> listOf("Parks", "Hotels", "Restaurants", "Cafes", "Religious Sites")
            "Jericho" -> listOf("Water Parks", "Hotels", "Restaurants", "Cafes", "Religious Sites", "Special Sites")
            "Ramallah" -> listOf("Hotels", "Restaurants", "Cafes", "Cinema", "Activities", "Pools")
            "Hebron" -> listOf("Parks", "Hotels", "Restaurants", "Cafes", "Religious Sites", "Special Sites")
            "Nablus" -> listOf("Parks", "Hotels", "Restaurants", "Cafes", "Religious Sites", "Special Sites")
            else -> listOf("Restaurants", "Hotels", "Attractions", "Malls", "Cafes", "Parks")
        }

        for (i in categories.indices) {
            buttons[i].text = categories[i]
            buttons[i].setOnClickListener {
                updatePlaces(cityName, categories[i])
            }
        }

        for (i in categories.size until buttons.size) {
            buttons[i].visibility = View.GONE
        }
    }

    private fun updatePlaces(city: String?, category: String) {
        val places = when (city) {
            "Ramallah" -> when (category) {
                "Hotels" -> listOf(
                    Place(R.drawable.mill, "Millennium Palestine Ramallah\n" +
                            "Address: Emil Habib Street, Al Masyoun, Ramallah, Palestine"),
                    Place(R.drawable.carmel_r, "Carmel Hotel \n" +
                            "Address: Al Jihad Street, Al Masyoun, Ramallah, Palestine."),
                    Place(R.drawable.hotel_r, "Ankars Suites & Hotel \n" +
                            "Address: Building No.13, Ahmad Al-Dijani St., Al Masyoun, Ramallah, Palestine.")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.lazaward, "Lazaward\n" +
                            "Address: Altira Street, George Habash Square, Ramallah, State of Palestine. "),
                    Place(R.drawable.reef_r, " Reef restaurant & Cafe\n" +
                            "Address: Al‑Tireh Street, Ramallah & Al‑Bireh, State of Palestine."),
                    Place(R.drawable.cavier_r, "Caviar Seafood Ramallah\n" +
                            "Address: Al‑Tirah (Hai Al Tirah) Street, Ramallah & Al‑Bireh, State of Palestine.")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.lab_r, "Name: Coffee Lab\n" +
                            "Address: Al Ersal Street (Opposite Aladdin Sweets), Al‑Irsal, Ramallah, State of Palestine."),
                    Place(R.drawable.es_r, "Espresso Shot\n" +
                            "Address: Al‑Tireh & Al‑Masyoun, Ramallah & Al‑Bireh, State of Palestine."),
                    Place(R.drawable.coff_r, "Qahwa Cafe\n" +
                            "Address: Al‑Nahda Street (formerly “Amara Funduq Al‑Wahda”), Ramallah, State of Palestine.")
                )
                "Cinema" -> listOf(
                    Place(R.drawable.cinema_r, "iCON Cinema – iCON Mall\n" +
                            "Address: Third Floor, iCON Mall, Surda Main Street, Ramallah, State of Palestine."),
                    Place(R.drawable.cinema_r2, "Palestine Trade tower\n" +
                            "Address: Al‑Ersal Street, Ramallah, State of Palestine."),
                )
                "Activities" -> listOf(
                    Place(R.drawable.ignit, "IGNITE Palestine\n" +
                            "Address: Main Street, Bazaar Complex, Al‑Tireh, Ramallah, State of Palestine"),
                    Place(R.drawable.car_r, "Let’s Go Karting\n" +
                            "Address: After Huda Fuel Station, Qundeel Kasarat Alley, Al‑Masyoun, Ramallah & Al‑Bireh, State of Palestine"),
                    Place(R.drawable.vr_r, "VR Zone\n" +
                            "Address: Ramallah, Al‑Masyoun, Al‑Barriya St, State of Palestine.")
                )
                "Pools" -> listOf(
                    Place(R.drawable.regalo_r, "Regalo Pools (Regalo — Tira and Acwazon / Al-Masyoun)\n" +
                            "Address: Al Tireh / Al‑Masyoun, Ramallah, State of Palestine"),
                    Place(R.drawable.snobar_r, "Snowbar Restaurant,Pool & Bar\n" +
                            "Address: Near Y‑College, Ein Sinya, Ramallah & Al‑Bireh, State of Palestine."),
                    Place(R.drawable.po_r, "AquaZan\n" +
                            "Address: Al‑Masyoun, Ramallah & Al‑Bireh, State of Palestine.")
                )
                else -> emptyList()
            }
            "Nablus" -> when (category) {
                "Parks" -> listOf(
                    Place(R.drawable.logo, "Example Park 1"),
                    Place(R.drawable.logo, "Example Park 2"),
                    Place(R.drawable.logo, "Example Park 3")
                )
                "Hotels" -> listOf(
                    Place(R.drawable.hesham, "Hesham Palace"),
                    Place(R.drawable.logo, "Example Hotel 2"),
                    Place(R.drawable.logo, "Example Hotel 3")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.logo, "Example Restaurant 1"),
                    Place(R.drawable.logo, "Example Restaurant 2"),
                    Place(R.drawable.logo, "Example Restaurant 3")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.logo, "Example Cafe 1"),
                    Place(R.drawable.logo, "Example Cafe 2"),
                    Place(R.drawable.logo, "Example Cafe 3")
                )
                "Religious Sites" -> listOf(
                    Place(R.drawable.logo, "Example Site 1"),
                    Place(R.drawable.logo, "Example Site 2"),
                    Place(R.drawable.logo, "Example Site 3")
                )
                "Special Sites" -> listOf(
                    Place(R.drawable.logo, "Example Special Site 1"),
                    Place(R.drawable.logo, "Example Special Site 2"),
                    Place(R.drawable.logo, "Example Special Site 3")
                )
                else -> emptyList()
            }
             "Bethlehem" -> when (category) {
                "Parks" -> listOf(
                    Place(R.drawable.logo, "Example Park 1"),
                    Place(R.drawable.logo, "Example Park 2"),
                    Place(R.drawable.logo, "Example Park 3")
                )
                "Hotels" -> listOf(
                    Place(R.drawable.logo, "Example Hotel 1"),
                    Place(R.drawable.logo, "Example Hotel 2"),
                    Place(R.drawable.logo, "Example Hotel 3")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.logo, "Example Restaurant 1"),
                    Place(R.drawable.logo, "Example Restaurant 2"),
                    Place(R.drawable.logo, "Example Restaurant 3")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.logo, "Example Cafe 1"),
                    Place(R.drawable.logo, "Example Cafe 2"),
                    Place(R.drawable.logo, "Example Cafe 3")
                )
                "Religious Sites" -> listOf(
                    Place(R.drawable.logo, "Example Site 1"),
                    Place(R.drawable.logo, "Example Site 2"),
                    Place(R.drawable.logo, "Example Site 3")
                )
                else -> emptyList()
            }
            "Jericho" -> when (category) {
                "Water Parks" -> listOf(
                    Place(R.drawable.banna, "Example Water Park 1"),
                    Place(R.drawable.water_1, "Example Water Park 2"),
                    Place(R.drawable.se_j, "Example Water Park 3")
                )
                "Hotels" -> listOf(
                    Place(R.drawable.jasem, "Example Hotel 1"),
                    Place(R.drawable.hotel_j3, "Example Hotel 2"),
                    Place(R.drawable.hotel_j, "Example Hotel 3")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.rec_j, "Example Restaurant 1"),
                    Place(R.drawable.rec_j2, "Example Restaurant 2"),
                    Place(R.drawable.rec_j2, "Example Restaurant 3")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.logo, "Example Cafe 1"),
                    Place(R.drawable.logo, "Example Cafe 2"),
                    Place(R.drawable.logo, "Example Cafe 3")
                )

                "Special Sites" -> listOf(
                    Place(R.drawable.hesham, "Example Special Site 1"),
                    Place(R.drawable.logo, "Example Special Site 2"),
                    Place(R.drawable.logo, "Example Special Site 3")
                )
                else -> emptyList()
            }
            "Hebron" -> when (category) {
                "Parks" -> listOf(
                    Place(R.drawable.ph1, "Palm - Hebron Park"),
                    Place(R.drawable.ph2, "Ain Sarah, Hebron"),
                    Place(R.drawable.ph3, "Example Park 3")
                )
                "Hotels" -> listOf(
                    Place(R.drawable.hh1, "Example Hotel 1"),
                    Place(R.drawable.hh2, "Example Hotel 2"),

                )
                "Restaurants" -> listOf(
                    Place(R.drawable.rech1, "Example Restaurant 1"),
                    Place(R.drawable.rech2, "Example Restaurant 2"),
                    Place(R.drawable.rech3, "Example Restaurant 3")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.coff_h, "Example Cafe 1"),
                    Place(R.drawable.caffh1, "Example Cafe 2"),
                    Place(R.drawable.caffh2, "Example Cafe 3")
                )
                "Religious Sites" -> listOf(
                    Place(R.drawable.sh1, "Cave of the Patriarchs / Al Ibrahimi Mosque \n" +
                            "Emek Hebron St 770, Hebron"),
                    Place(R.drawable.sh2, "Al-maskobiyya Church Al-jalda, Hebron"),
                    Place(R.drawable.sh3, "Tel Rumeida")
                )
                "Special Sites" -> listOf(
                    Place(R.drawable.sh4, "The visitor center \"Touching eternity\""),
                    Place(R.drawable.sh5, "Hebron Glass & Ceramics Shops"),
                    Place(R.drawable.sh6, "The Old City")
                )
                else -> emptyList()
            }
            else -> emptyList()
        }

        placeAdapter = PlaceAdapter(places)
        detailsRecyclerView.adapter = placeAdapter
    }
}
