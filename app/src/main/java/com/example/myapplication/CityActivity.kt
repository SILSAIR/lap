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
        ThemeManager.applyTheme(this)
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
            "Jericho" -> listOf("Water Parks", "Parks", "Hotels", "Restaurants", "Cafes", "Special Sites")
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

        // Load initial category
        if (categories.isNotEmpty()) {
            updatePlaces(cityName, categories[0])
        }
    }

    private fun updatePlaces(city: String?, category: String) {
        val places = when (city) {
            "Ramallah" -> when (category) {
                "Hotels" -> listOf(
                    Place(R.drawable.mill, "Millennium Travelo Ramallah", "Emil Habib Street, Al Masyoun, Ramallah, Travelo", "24/7", "31.9023, 35.2063"),
                    Place(R.drawable.carmel_r, "Carmel Hotel", "Al Jihad Street, Al Masyoun, Ramallah, Travelo", "24/7", "31.9074, 35.1943"),
                    Place(R.drawable.hotel_r, "Ankars Suites & Hotel", "Building No.13, Ahmad Al-Dijani St., Al Masyoun, Ramallah, Travelo", "24/7", "31.9063, 35.1979")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.lazaward, "Lazaward", "Altira Street, George Habash Square, Ramallah, State of Travelo.", "12:00-00:00", "31.9103, 35.1953"),
                    Place(R.drawable.reef_r, "Reef restaurant & Cafe", "Al‑Tireh Street, Ramallah & Al‑Bireh, State of Travelo.", "10:00-01:00", "31.9130, 35.1916"),
                    Place(R.drawable.cavier_r, "Caviar Seafood Ramallah", "Al‑Tirah (Hai Al Tirah) Street, Ramallah & Al‑Bireh, State of Travelo.", "12:00-23:00", "31.9133, 35.1917")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.lab_r, "Coffee Lab", "Al Ersal Street (Opposite Aladdin Sweets), Al‑Irsal, Ramallah, State of Travelo.", "07:00-23:00", "31.9029, 35.2030"),
                    Place(R.drawable.es_r, "Espresso Shot", "Al‑Tireh & Al‑Masyoun, Ramallah & Al‑Bireh, State of Travelo.", "07:00-23:00", "31.9126, 35.1912"),
                    Place(R.drawable.coff_r, "Qahwa Cafe", "Al‑Nahda Street (formerly “Amara Funduq Al‑Wahda”), Ramallah, State of Travelo.", "08:00-00:00", "31.9029, 35.2030")
                )
                "Cinema" -> listOf(
                    Place(R.drawable.cinema_r, "iCON Cinema – iCON Mall", "Third Floor, iCON Mall, Surda Main Street, Ramallah, State of Travelo.", "17:00-01:00", "31.9213, 35.2033"),
                    Place(R.drawable.cinema_r2, "Palestine Trade tower", "Al‑Ersal Street, Ramallah, State of Travelo.", "17:00-01:00", "31.9029, 35.2030")
                )
                "Activities" -> listOf(
                    Place(R.drawable.ignit, "IGNITE Travelo", "Main Street, Bazaar Complex, Al‑Tireh, Ramallah, State of Travelo", "10:00-22:00", "31.9133, 35.1917"),
                    Place(R.drawable.car_r, "Let’s Go Karting", "After Huda Fuel Station, Qundeel Kasarat Alley, Al‑Masyoun, Ramallah & Al‑Bireh, State of Travelo", "14:00-22:00", "31.9113, 35.1872"),
                    Place(R.drawable.vr_r, "VR Zone", "Ramallah, Al‑Masyoun, Al‑Barriya St, State of Travelo.", "12:00-22:00", "31.9079, 35.1931")
                )
                "Pools" -> listOf(
                    Place(R.drawable.regalo_r, "Regalo Pools (Regalo — Tira and Acwazon / Al-Masyoun)", "Al Tireh / Al‑Masyoun, Ramallah, State of Travelo", "10:00-19:00", "31.9130, 35.1916"),
                    Place(R.drawable.snobar_r, "Snowbar Restaurant,Pool & Bar", "Near Y‑College, Ein Sinya, Ramallah & Al‑Bireh, State of Travelo.", "10:00-22:00", "31.9863, 35.2141"),
                    Place(R.drawable.po_r, "AquaZan", "Al‑Masyoun, Ramallah & Al‑Bireh, State of Travelo.", "10:00-19:00", "31.9074, 35.1943")
                )
                else -> emptyList()
            }
            "Nablus" -> when (category) {
                "Parks" -> listOf(
                    Place(R.drawable.np1, "Nablus Park", "Mount Ebal about 4 km from the Old City 3.9", "24/7", "32.2210, 35.2622"),
                    Place(R.drawable.np2, "Sama Nablus Park", "Mount Ebal, Nablus 4.0", "24/7", "32.2210, 35.2622"),
                    Place(R.drawable.np3, "Family Park", "Beginning of Rafidia - Al Muntazah Street4.0", "09:00-23:00", "32.2210, 35.2622")
                )
                "Hotels" -> listOf(
                    Place(R.drawable.nh1, "Golden Rose Hotel", "Rafidia near An-Najah University campus 3.8", "24/7", "32.2210, 35.2622"),
                    Place(R.drawable.nh2, "Royal Suiets Hotel", "Rafidya St. Nablus 4.3", "24/7", "32.2210, 35.2622"),
                    Place(R.drawable.nh3, "Golden Tree Hotel", "Bait Wazan, Nablus 4.3", "24/7", "32.2210, 35.2622")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.rn1, "Alf Layla W Layla", "Nihayat Sharie Tunis, Nablus 4.1", "12:00-00:00", "32.2210, 35.2622"),
                    Place(R.drawable.rn2, "Tanoreen restaurant", "Nablus - Tunis Street - Opposite Umm Salama Mosque 4.0", "12:00-00:00", "32.2210, 35.2622"),
                    Place(R.drawable.rn3, "W Restaurant", "End of Tunis Street 3.9", "12:00-00:00", "32.2210, 35.2622")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.cn1, "Veranda Cafe & Cultural Space", "Rafeedia St., Nablus 4.4", "08:00-00:00", "32.2210, 35.2622"),
                    Place(R.drawable.cn2, "Cedarz Gelato & Coffee House", "Rafedia Opposite Korean Institute- Al Najah Academy, Nablus 5.0", "10:00-23:00", "32.2210, 35.2622"),
                    Place(R.drawable.cn3, "Nosha Cafe", "Nablus", "10:00-23:00", "32.2210, 35.2622")
                )
                "Religious Sites" -> listOf(
                    // Placeholder for Nablus Religious Sites
                )
                "Special Sites" -> listOf(
                    Place(R.drawable.sn1, "Old City Shopping Centre", "Nablus", "09:00-20:00", "32.2210, 35.2622"),
                    Place(R.drawable.sn2, "Albader Soap Factory", "Alnaser Street Old City beside Alnaser Mosque، Nablus / West Bank", "09:00-17:00", "32.2210, 35.2622"),
                    Place(R.drawable.sn3, "An-Najah National University Museum", "An-Najah National University، Old Campus Street 7, Nablus", "09:00-15:00", "32.2210, 35.2622")
                )
                else -> emptyList()
            }
            "Bethlehem" -> when (category) {
                "Parks" -> listOf(
                    Place(R.drawable.pb1, "Mary Doty Children's Park", "Bethlehem, State of Travelo.", "24/7", "31.7048, 35.2037"),
                    Place(R.drawable.pb2, "Solomon’s Pools", "Near al‑Khader village, about 3.5km southwest of Bethlehem, State of Travelo.", "09:00-17:00", "31.6787, 35.1634"),
                    Place(R.drawable.pb3, "Herodium National Park", "Bethlehem Governorate, State of Travelo.", "08:00-17:00", "31.6577, 35.2415")
                )
                "Hotels" -> listOf(
                    Place(R.drawable.hb1, "Manger Square Hotel – Betlhehem", "Manger Square, Bethlehem", "24/7", "31.7048, 35.2037"),
                    Place(R.drawable.hb2, "Lotus Boutique Hotel - Betlhehem", "Shepherds Field, Beit Sahour", "24/7", "31.7048, 35.2037"),
                    Place(R.drawable.hb3, "Grand Hotel - Betlhehem", "Manger St, Bethlehem", "24/7", "31.7048, 35.2037")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.rb1, "Afteem Restaurant - Betlhehem", "Manger Square, Bethlehem", "10:00-22:00", "31.7048, 35.2037"),
                    Place(R.drawable.rb2, "The Tent Restaurant", "Beit Sahour (between Shepherd’s Fields and Church of the Nativity), Bethlehem, State of Travelo.", "10:00-22:00", "31.7048, 35.2037")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.cb1, "Bonjour Cafe - betlhehem", "Manger St, Bethlehem", "08:00-23:00", "31.7048, 35.2037"),
                    Place(R.drawable.cb2, "Stars & Bucks Cafe - betlhehem", "Manger Square, Bethlehem", "08:00-23:00", "31.7048, 35.2037"),
                    Place(R.drawable.cb3, "Singer Cafe - betlhehem", "Manger Square, Bethlehem", "08:00-23:00", "31.7048, 35.2037")
                )
                "Religious Sites" -> listOf(
                    Place(R.drawable.reb1, "Milk Grotto", "Beit Jala, Bethlehem Governorate", "08:00-17:00", "31.7048, 35.2037"),
                    Place(R.drawable.sb1, "Beit Jala Castle", "Beit Jala, Bethlehem Governorate", "24/7", "31.7048, 35.2037"),
                    Place(R.drawable.church, "Church of the Nativity", "Manger Square, Bethlehem", "05:00-20:00", "31.7048, 35.2037")
                )
                else -> emptyList()
            }
            "Jericho" -> when (category) {
                "Water Parks" -> listOf(
                    Place(R.drawable.banna, "Banana water park", "In the \"Ain al‑Sultan\" / Al-Mo‘arajat Road / Ein al-Dyouk area Jericho", "10:00-18:00", "31.8616, 35.4424"),
                    Place(R.drawable.pp1, "Safari aqua park", "Japan Street, southern entrance to Jericho", "10:00-18:00", "31.8616, 35.4424")
                )
                "Parks" -> listOf(
                    Place(R.drawable.pp2, "Spanish park", "The park is listed by the local municipality under \"Amman Street – city center\"", "24/7", "31.8616, 35.4424"),
                    Place(R.drawable.jp2, "Jericho Botanical Garden", "north of Jericho city, on the road toward Al-Auja", "08:00-16:00", "31.8616, 35.4424")
                )
                "Hotels" -> listOf(
                    Place(R.drawable.hh1, "Oasis Hotel Jericho", "Jerusalem Road, PO Box 150, Jericho", "24/7", "31.8616, 35.4424"),
                    Place(R.drawable.hh2, "Jericho resort village", "Bisan Street, near Hisham’s Palace, 162 Jericho,", "24/7", "31.8616, 35.4424"),
                    Place(R.drawable.hh3, "Al baiara resort", "Al Maghtas Street, Jericho", "24/7", "31.8616, 35.4424")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.jr1, "Green Valley Restaurant", "Ein Al Sultan Street, Jericho.", "10:00-23:00", "31.8616, 35.4424"),
                    Place(R.drawable.jr2, "Temptation Restaurant", "Tel es-Sultan / Abu Raed, near the old city of Jericho.", "10:00-23:00", "31.8616, 35.4424"),
                    Place(R.drawable.jr3, "Sultana Restaurant", "on Ein el Duyuk Road in Jericho, near the northern entrance of the city.", "10:00-23:00", "31.8616, 35.4424")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.jc1, "21Café Jericho", "Jerusalem Street, Jericho.", "08:00-00:00", "31.8616, 35.4424"),
                    Place(R.drawable.jc2, "Rio Café", "Jerusalem Street, Jericho. ", "08:00-00:00", "31.8616, 35.4424"),
                    Place(R.drawable.jc3, "Teacher Cafe", "In the centre of Jericho.", "08:00-00:00", "31.8616, 35.4424")
                )
                "Special Sites" -> listOf(
                    Place(R.drawable.hesham, "Hisham palace", "This is an Umayyad era palace located just north of Jericho", "08:00-17:00", "31.8616, 35.4424"),
                    Place(R.drawable.rh2, "Dead Sea", "The Dead Sea is located near Jericho to the west", "24/7", "31.8616, 35.4424"),
                    Place(R.drawable.rh3, "Jericho Cable Car (Teleferik)", "teleferik that links Jericho (Elisha’s Spring) up to the Mount of Temptation via a 1,300‑m route,", "08:00-21:00", "31.8616, 35.4424")
                )
                else -> emptyList()
            }
            "Hebron" -> when (category) {
                "Parks" -> listOf(
                    Place(R.drawable.ph1, "Palm - Hebron Park", "Hebron", "24/7", "31.5326, 35.0998"),
                    Place(R.drawable.ph2, "Ain Sarah, Hebron", "Hebron", "24/7", "31.5326, 35.0998"),
                    Place(R.drawable.ph3, "Example Park 3", "Hebron", "24/7", "31.5326, 35.0998")
                )
                "Hotels" -> listOf(
                    Place(R.drawable.hh11, "Friends Hostel", "Old shalala street, Bab Al_zawiye, Hebron, Travelo, Hebron 4.8", "24/7", "31.5326, 35.0998"),
                    Place(R.drawable.hh21, "Queen Plaza Hotel", "Ain Sarah, Hebron", "24/7", "31.5326, 35.0998")
                )
                "Restaurants" -> listOf(
                    Place(R.drawable.rech1, "Roza restaurant", "Hebron, Ain Sarah", "12:00-00:00", "31.5326, 35.0998"),
                    Place(R.drawable.rech2, "Hawana Restaurant and cafe", "Ain Sara st, Hebron", "12:00-00:00", "31.5326, 35.0998"),
                    Place(R.drawable.rech3, "Abu Mazen Restaurant", "Hebron, Namera Street", "12:00-00:00", "31.5326, 35.0998")
                )
                "Cafes" -> listOf(
                    Place(R.drawable.coff_h, "Ain sara street 4.5", "Hebron", "08:00-00:00", "31.5326, 35.0998"),
                    Place(R.drawable.caffh1, "Ain Sarah, Hebron", "Hebron", "08:00-00:00", "31.5326, 35.0998"),
                    Place(R.drawable.caffh2, "Issa street, Hebron 4.7", "Hebron", "08:00-00:00", "31.5326, 35.0998")
                )
                "Religious Sites" -> listOf(
                    Place(R.drawable.sh1, "Cave of the Patriarchs / Al Ibrahimi Mosque", "Emek Hebron St 770, Hebron", "04:00-21:00", "31.5326, 35.0998"),
                    Place(R.drawable.sh2, "Al-maskobiyya Church", "Al-jalda, Hebron", "09:00-17:00", "31.5326, 35.0998"),
                    Place(R.drawable.sh3, "Tel Rumeida", "Hebron", "24/7", "31.5326, 35.0998")
                )
                "Special Sites" -> listOf(
                    Place(R.drawable.sh4, "The visitor center \"Touching eternity\"", "Hebron", "09:00-17:00", "31.5326, 35.0998"),
                    Place(R.drawable.sh5, "Hebron Glass & Ceramics Shops", "Hebron", "09:00-17:00", "31.5326, 35.0998"),
                    Place(R.drawable.sh6, "The Old City", "Hebron", "24/7", "31.5326, 35.0998")
                )
                else -> emptyList()
            }
            else -> emptyList()
        }

        placeAdapter = PlaceAdapter(places, category)
        detailsRecyclerView.adapter = placeAdapter
    }
}
