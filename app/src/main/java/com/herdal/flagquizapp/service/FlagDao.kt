package com.herdal.flagquizapp.service

import android.annotation.SuppressLint
import com.herdal.flagquizapp.model.Flag

// Dabase Access Object
class FlagDao {
    // get random 5 flags
    @SuppressLint("Range")
    fun getRandom5Flags(dbHelper: DatabaseHelper) : ArrayList<Flag> {
        val flagList = ArrayList<Flag>()
        val db = dbHelper.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)

        while(c.moveToNext()) {
            val flag = Flag(
                c.getInt(c.getColumnIndex("bayrak_id"))
                ,c.getString(c.getColumnIndex("bayrak_ad"))
                ,c.getString(c.getColumnIndex("bayrak_resim"))
            )
            flagList.add(flag)
        }
        return flagList
    }
    // get random 5 wrong answers
    @SuppressLint("Range")
    fun getRandom3WrongAnswers(dbHelper: DatabaseHelper, flagId: Int) : ArrayList<Flag> {
        val flagList = ArrayList<Flag>()
        val db = dbHelper.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != $flagId ORDER BY RANDOM() LIMIT 3",null)

        while(c.moveToNext()) {
            val flag = Flag(
                c.getInt(c.getColumnIndex("bayrak_id"))
                ,c.getString(c.getColumnIndex("bayrak_ad"))
                ,c.getString(c.getColumnIndex("bayrak_resim"))
            )
            flagList.add(flag)
        }
        return flagList
    }
}