package com.herdal.flagquizapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,"bayrakquiz.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) { // table definition
        db?.execSQL("CREATE TABLE IF NOT EXISTS 'flags' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' TEXT, 'image' TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS flags")
        onCreate(db)
    }
}