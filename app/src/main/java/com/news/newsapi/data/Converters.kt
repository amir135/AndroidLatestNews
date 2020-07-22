package com.news.newsapi.data

import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromInstant(value: Date): Long {
            return value.time
        }

        @TypeConverter
        @JvmStatic
        fun toInstant(value: Long): Date {
            return Date(value)
        }
    }


}