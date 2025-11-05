package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class RegisterPersonalInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_personal_info)

        val nextButton = findViewById<Button>(R.id.nextButton)
        val firstNameEditText = findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = findViewById<EditText>(R.id.lastNameEditText)
        val dobEditText = findViewById<EditText>(R.id.dobEditText)

        dobEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    dobEditText.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        nextButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("FIRST_NAME", firstNameEditText.text.toString())
            intent.putExtra("LAST_NAME", lastNameEditText.text.toString())
            intent.putExtra("DOB", dobEditText.text.toString())
            startActivity(intent)
        }
    }
}
