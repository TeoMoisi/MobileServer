package com.example.ifly.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FlightDao {
    @Query("SELECT * FROM flights")
    fun getAll(): LiveData<List<Flight>>

    @Insert
    suspend fun insert(flight: Flight)

    @Insert
    fun insertTest(flight: Flight)

    @Delete
    suspend fun delete(flight: Flight)

    @Query("DELETE FROM flights")
    suspend fun deleteAll()

    @Update
    suspend fun updateFlight(flight: Flight)

    @Query("SELECT * from flights where id = :id LIMIT 1")
    fun getFlightById(id: Int): Flight
}