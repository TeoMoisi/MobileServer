package com.example.ifly.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Flight::class], version = 1)
abstract class FlightRoomDatabase : RoomDatabase() {

    abstract fun flightDao(): FlightDao

    companion object {
        @Volatile
        private var INSTANCE: FlightRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): FlightRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlightRoomDatabase::class.java,
                    "flight_db7"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}