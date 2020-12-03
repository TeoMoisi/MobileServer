package com.example.ifly

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.ifly.model.Flight
import com.example.ifly.model.FlightDao
import com.example.ifly.network.FlightApi

class FlightRepository(private val flightDao: FlightDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allFlights: LiveData<List<Flight>> = flightDao.getAll()

    suspend fun insert(flight: Flight) {
        flightDao.insert(flight)
    }

    suspend fun delete(flight: Flight) {
        flightDao.delete(flight)
    }

    suspend fun update(flight: Flight) {
        flightDao.updateFlight(flight)
    }

    suspend fun deleteAll() {
        flightDao.deleteAll()
    }
}