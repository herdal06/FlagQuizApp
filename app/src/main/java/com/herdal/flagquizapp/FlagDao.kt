package com.herdal.flagquizapp

import android.annotation.SuppressLint

// Dabase Access Object
class FlagDao {
    // get random 5 flags
    @SuppressLint("Range")
    fun getRandom5Flags(dbHelper: DatabaseHelper) : ArrayList<Flag> {
        val flagList = ArrayList<Flag>()
        val db = dbHelper.writableDatabase
        val c = db.rawQuery("SELECT * FROM flags ORDER BY RANDOM() LIMIT 5",null)

        while(c.moveToNext()) {
            val flag = Flag(
                c.getInt(c.getColumnIndex("id"))
                ,c.getString(c.getColumnIndex("name"))
                ,c.getString(c.getColumnIndex("image"))
            )
            flagList.add(flag)
        }
        return flagList
    }
}