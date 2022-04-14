package com.herdal.flagquizapp.service

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,"bayrakquiz.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) { // table definition
        db?.execSQL("CREATE TABLE IF NOT EXISTS 'bayraklar' ('bayrak_id' INTEGER PRIMARY KEY AUTOINCREMENT, 'bayrak_ad' TEXT, 'bayrak_resim' TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS bayraklar")
        onCreate(db)
    }
}