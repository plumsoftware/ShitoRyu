package com.example.shitoryu.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shitoryu.model.EventData

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "events.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_EVENTS = "events"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_DAY_OF_MONTH = "_day_of_month"
        private const val COLUMN_MONTH = "_month"
        private const val COLUMN_YEAR = "_year"
        private const val COLUMN_HOUR = "_hour"
        private const val COLUMN_MINUTE = "_minute"
        private const val COLUMN_IS_COMPETITION = "_is_competition"
        private const val COLUMN_PLACE = "_place"
        private const val COLUMN_GROUP = "_group"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_EVENTS " +
                    "($COLUMN_ID INTEGER PRIMARY KEY, " +
                    "$COLUMN_DAY_OF_MONTH INTEGER, " +
                    "$COLUMN_MONTH INTEGER, " +
                    "$COLUMN_YEAR INTEGER, " +
                    "$COLUMN_HOUR INTEGER, " +
                    "$COLUMN_MINUTE INTEGER, " +
                    "$COLUMN_IS_COMPETITION INTEGER, " +
                    "$COLUMN_PLACE TEXT, " +
                    "$COLUMN_GROUP TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EVENTS")
        onCreate(db)
    }

    @SuppressLint("Range")
    fun getEvents(): List<EventData> {
        val eventList = mutableListOf<EventData>()
        val query = "SELECT * FROM $TABLE_EVENTS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val dayOfMonth = cursor.getInt(cursor.getColumnIndex(COLUMN_DAY_OF_MONTH))
                val month = cursor.getInt(cursor.getColumnIndex(COLUMN_MONTH))
                val year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR))
                val hour = cursor.getInt(cursor.getColumnIndex(COLUMN_HOUR))
                val minute = cursor.getInt(cursor.getColumnIndex(COLUMN_MINUTE))
                val isCompetition = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_COMPETITION))
                val place = cursor.getString(cursor.getColumnIndex(COLUMN_PLACE))
                val group = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP))

                val eventData = EventData(
                    dayOfMonth = dayOfMonth,
                    month = month,
                    year = year,
                    hour = hour,
                    minute = minute,
                    isCompetition = isCompetition,
                    place = place,
                    group = group
                )
                eventList.add(eventData)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return eventList
    }

    fun addEvent(data: EventData) {
        val values = ContentValues()
        values.put(COLUMN_DAY_OF_MONTH, data.dayOfMonth)
        values.put(COLUMN_MONTH, data.month)
        values.put(COLUMN_YEAR, data.year)
        values.put(COLUMN_HOUR, data.hour)
        values.put(COLUMN_MINUTE, data.minute)
        values.put(COLUMN_IS_COMPETITION, data.isCompetition)
        values.put(COLUMN_PLACE, data.place)
        values.put(COLUMN_GROUP, data.group)

        val db = this.writableDatabase
        db.insert(TABLE_EVENTS, null, values)
        db.close()
    }

    fun updateEvent(id: Int, data: EventData) {
        val values = ContentValues()
        values.put(COLUMN_DAY_OF_MONTH, data.dayOfMonth)
        values.put(COLUMN_MONTH, data.month)
        values.put(COLUMN_YEAR, data.year)
        values.put(COLUMN_HOUR, data.hour)
        values.put(COLUMN_MINUTE, data.minute)
        values.put(COLUMN_IS_COMPETITION, data.isCompetition)
        values.put(COLUMN_PLACE, data.place)
        values.put(COLUMN_GROUP, data.group)

        val db = this.writableDatabase
        db.update(TABLE_EVENTS, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }
}