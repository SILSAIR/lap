package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class User(val id: Long, val username: String, val password: String)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserManager.db"
        private const val TABLE_USER = "user"
        private const val KEY_ID = "id"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT UNIQUE,"
                + KEY_PASSWORD + " TEXT" + ")")
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER)
        onCreate(db)
    }

    fun addUser(username: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_USERNAME, username)
        contentValues.put(KEY_PASSWORD, password)
        val success = db.insert(TABLE_USER, null, contentValues)
        db.close()
        return success
    }

    fun getUser(username: String, password: String): User? {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(
            TABLE_USER,
            arrayOf(KEY_ID, KEY_USERNAME, KEY_PASSWORD),
            "$KEY_USERNAME = ? AND $KEY_PASSWORD = ?",
            arrayOf(username, password),
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val user = User(
                id = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(KEY_USERNAME)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PASSWORD))
            )
            cursor.close()
            db.close()
            return user
        }
        cursor.close()
        db.close()
        return null
    }
}