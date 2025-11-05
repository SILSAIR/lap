package com.example.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

data class Place(
    val imageRes: Int,
    val name: String,
    val address: String,
    val hours: String,
    val coordinates: String
)

class PlaceAdapter(private val places: List<Place>, private val category: String) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(places[position], category)
    }

    override fun getItemCount(): Int = places.size

    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.placeImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.placeNameTextView)
        private val detailsTextView: TextView = itemView.findViewById(R.id.placeDetailsTextView)
        private val bookButton: Button = itemView.findViewById(R.id.bookButton)

        fun bind(place: Place, category: String) {
            imageView.setImageResource(place.imageRes)
            nameTextView.text = place.name
            detailsTextView.text = "Address: ${place.address}\nHours: ${place.hours}\nCoordinates: ${place.coordinates}"

            imageView.setOnClickListener {
                val context = it.context
                val intent = Intent(context, ZoomActivity::class.java)
                intent.putExtra("IMAGE_RES", place.imageRes)
                context.startActivity(intent)
            }

            when (category) {
                "Hotels" -> {
                    bookButton.text = "Book Now"
                    bookButton.visibility = View.VISIBLE
                    bookButton.setOnClickListener {
                        showBookingDialog(place)
                    }
                }
                "Restaurants" -> {
                    bookButton.text = "Reserve Table"
                    bookButton.visibility = View.VISIBLE
                    bookButton.setOnClickListener {
                        showRestaurantDialog(place)
                    }
                }
                else -> {
                    bookButton.visibility = View.GONE
                }
            }
        }

        private fun showBookingDialog(place: Place) {
            val builder = AlertDialog.Builder(itemView.context)
            val inflater = LayoutInflater.from(itemView.context)
            val dialogView = inflater.inflate(R.layout.dialog_booking, null)
            builder.setView(dialogView)

            val hotelNameTextView = dialogView.findViewById<TextView>(R.id.hotelNameTextView)
            hotelNameTextView.text = place.name

            val adultsCountTextView = dialogView.findViewById<TextView>(R.id.adultsCountTextView)
            val childrenCountTextView = dialogView.findViewById<TextView>(R.id.childrenCountTextView)
            val roomsCountTextView = dialogView.findViewById<TextView>(R.id.roomsCountTextView)

            var adults = 2
            var children = 0
            var rooms = 1

            dialogView.findViewById<Button>(R.id.adultsMinusButton).setOnClickListener {
                if (adults > 1) adults--
                adultsCountTextView.text = adults.toString()
            }
            dialogView.findViewById<Button>(R.id.adultsPlusButton).setOnClickListener {
                adults++
                adultsCountTextView.text = adults.toString()
            }
            dialogView.findViewById<Button>(R.id.childrenMinusButton).setOnClickListener {
                if (children > 0) children--
                childrenCountTextView.text = children.toString()
            }
            dialogView.findViewById<Button>(R.id.childrenPlusButton).setOnClickListener {
                children++
                childrenCountTextView.text = children.toString()
            }
            dialogView.findViewById<Button>(R.id.roomsMinusButton).setOnClickListener {
                if (rooms > 1) rooms--
                roomsCountTextView.text = rooms.toString()
            }
            dialogView.findViewById<Button>(R.id.roomsPlusButton).setOnClickListener {
                rooms++
                roomsCountTextView.text = rooms.toString()
            }

            val checkInButton = dialogView.findViewById<Button>(R.id.checkInButton)
            val checkOutButton = dialogView.findViewById<Button>(R.id.checkOutButton)

            val calendar = Calendar.getInstance()

            val checkInDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                checkInButton.text = "${day}/${month + 1}/${year}"
            }

            val checkOutDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                checkOutButton.text = "${day}/${month + 1}/${year}"
            }

            checkInButton.setOnClickListener {
                DatePickerDialog(
                    itemView.context,
                    checkInDateListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            checkOutButton.setOnClickListener {
                DatePickerDialog(
                    itemView.context,
                    checkOutDateListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }


            val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
            val dialog = builder.create()

            confirmButton.setOnClickListener {
                val message = "Booking Confirmed: $adults Adults, $children Children, $rooms Rooms\n" +
                        "From: ${checkInButton.text} to ${checkOutButton.text}"
                Toast.makeText(itemView.context, message, Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun showRestaurantDialog(place: Place) {
            val builder = AlertDialog.Builder(itemView.context)
            val inflater = LayoutInflater.from(itemView.context)
            val dialogView = inflater.inflate(R.layout.dialog_restaurant_reservation, null)
            builder.setView(dialogView)

            val restaurantNameTextView = dialogView.findViewById<TextView>(R.id.restaurantNameTextView)
            restaurantNameTextView.text = place.name

            val guestsCountTextView = dialogView.findViewById<TextView>(R.id.guestsCountTextView)
            var guests = 2

            dialogView.findViewById<Button>(R.id.guestsMinusButton).setOnClickListener {
                if (guests > 1) guests--
                guestsCountTextView.text = guests.toString()
            }
            dialogView.findViewById<Button>(R.id.guestsPlusButton).setOnClickListener {
                guests++
                guestsCountTextView.text = guests.toString()
            }

            val dateButton = dialogView.findViewById<Button>(R.id.dateButton)
            val timeButton = dialogView.findViewById<Button>(R.id.timeButton)
            val calendar = Calendar.getInstance()

            val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                dateButton.text = "${day}/${month + 1}/${year}"
            }

            val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                timeButton.text = "${hour}:${minute}"
            }

            dateButton.setOnClickListener {
                DatePickerDialog(
                    itemView.context,
                    dateListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            timeButton.setOnClickListener {
                TimePickerDialog(
                    itemView.context,
                    timeListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            }

            val confirmReservationButton = dialogView.findViewById<Button>(R.id.confirmReservationButton)
            val dialog = builder.create()

            confirmReservationButton.setOnClickListener {
                val message = "Reservation Confirmed: $guests Guests\n" +
                        "On: ${dateButton.text} at ${timeButton.text}"
                Toast.makeText(itemView.context, message, Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            dialog.show()
        }
    }
}
