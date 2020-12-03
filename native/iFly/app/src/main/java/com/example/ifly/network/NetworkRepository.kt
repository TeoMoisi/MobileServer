package com.example.ifly.network

import android.util.Log
import com.example.ifly.model.Flight
import kotlinx.serialization.StructureKind

object NetworkRepository {

    suspend fun getServerFlights(): List<Flight> {
        val flights = this.getFlightsFromServer()
        var result: MutableList<Flight> = ArrayList<Flight>()
        for (flight in flights) {
            Log.d("FLIGHT", flight.toString())
            Log.d("ID", flight.get("id").toString().split(".")[0])
            result.add(Flight(flight.get("id").toString().split(".")[0].toInt(), flight.get("datetime").toString(), flight.get("departure").toString(), flight.get("arrival").toString(), flight.get("code").toString(), flight.get("details").toString()))
        }
        Log.d("flights", result.toString())
        result.size > 0 || return emptyList()
        Log.d("RESULT", result.toString())
        return result
    }

    suspend fun saveFlight(flight: Flight): Map<String, String> {
        return this.addFlightToServer(flight)
    }

    suspend fun deleteFlightServer(id: Int) {
        return this.deleteFlightFromServer(id)
    }

    suspend fun updateFlightServer(id: Int, flight: Flight): Map<String, String> {
        return this.updateFlightFromServer(id, flight)
    }

    suspend fun sync(flights: List<Flight>) {
        return this.synchronize(flights)
    }

    private suspend fun getFlightsFromServer(): List<Map<String, String>> =
        FlightApi.service.getElements().get("flights")!!

    private suspend fun addFlightToServer(flight: Flight) =
        FlightApi.service.addFlight(flight)

    private suspend fun deleteFlightFromServer(id: Int) =
        FlightApi.service.deleteFlightById(id)

    private suspend fun updateFlightFromServer(id: Int, flight: Flight) =
        FlightApi.service.updateFlightFromNetwork(id, flight)

    private suspend fun synchronize(flights: List<Flight>) =
        FlightApi.service.synchronizeFlights(flights)
}
