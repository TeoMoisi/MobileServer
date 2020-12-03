package com.example.ifly

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ifly.model.Flight
import com.example.ifly.model.FlightDao
import com.example.ifly.model.FlightRoomDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var flightDao: FlightDao
    private lateinit var flightRoomDatabase: FlightRoomDatabase

//    private var mutableDBFlights = MutableLiveData<List<Flight>>().apply { value = emptyList() }
//    var flights: LiveData<List<Flight>> = mutableDBFlights

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        flightRoomDatabase = Room.inMemoryDatabaseBuilder(
            context, FlightRoomDatabase::class.java).build()
        flightDao = flightRoomDatabase.flightDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        flightRoomDatabase.close()
    }

    @Test
    fun testAddFlight() {

        var flight = Flight(100,"12/10/2019", "London", "Paris", "TYB67G", "")
        flightDao.insertTest(flight)

        val flightTest = flightDao.getFlightById(100)
        assertEquals(flightTest.datetime, flight.datetime)
    }

    @Test
    fun testDeleteFlight() = runBlocking {
        var flight = Flight(101,"12/10/2019", "London", "Paris", "TYB67G", "")

        flightDao.insert(flight)
        flightDao.delete(flight)

        assertEquals(flightDao.getFlightById(101), null)
    }
}
