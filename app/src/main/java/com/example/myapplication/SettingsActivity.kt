package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private var userId: Long = -1
    private lateinit var profileImageView: ImageView

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.applyTheme(this)
        setContentView(R.layout.activity_settings)

        databaseHelper = DatabaseHelper(this)
        userId = intent.getLongExtra("USER_ID", -1)

        val profileContainer = findViewById<LinearLayout>(R.id.profileContainer)
        val guestMessageTextView = findViewById<TextView>(R.id.guestMessageTextView)
        profileImageView = findViewById(R.id.profileImageView)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val saveChangesButton = findViewById<Button>(R.id.saveChangesButton)

        if (userId != -1L) {
            profileContainer.visibility = View.VISIBLE
            guestMessageTextView.visibility = View.GONE

            val user = databaseHelper.getUserById(userId)
            user?.let {
                usernameEditText.setText(it.username)
                passwordEditText.setText(it.password)
            }

            profileImageView.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
            }

            saveChangesButton.setOnClickListener {
                val newUsername = usernameEditText.text.toString()
                val newPassword = passwordEditText.text.toString()

                if (newUsername.isNotEmpty() && newPassword.isNotEmpty()) {
                    val updatedRows = databaseHelper.updateUser(userId, newUsername, newPassword)
                    if (updatedRows > 0) {
                        Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to save changes", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            profileContainer.visibility = View.GONE
            guestMessageTextView.visibility = View.VISIBLE
        }

        val darkModeSwitch = findViewById<Switch>(R.id.darkModeSwitch)
        val backToMainButton = findViewById<Button>(R.id.backToMainButton)

        darkModeSwitch.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ThemeManager.setTheme(this, AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                ThemeManager.setTheme(this, AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        backToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri = data.data!!
            profileImageView.setImageURI(imageUri)
        }
    }
}