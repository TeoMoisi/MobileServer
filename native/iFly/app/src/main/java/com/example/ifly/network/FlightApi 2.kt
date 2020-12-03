package com.example.ifly.network

import android.annotation.SuppressLint
import com.example.ifly.model.Flight
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

@SuppressLint("StaticFieldLeak")
object FlightApi {

    private const val URL = "http://10.0.2.2:2024/"

    interface Service {
        @GET("flights")
        suspend fun getElements(): Map<String, List<Map<String, String>>>// Observable<ProductEmbedded>
        @POST("flights")
        suspend fun addFlight(@Body flight: Flight): Map<String, String>
        @DELETE("flights/{id}")
        suspend fun deleteFlightById(@Path("id") id: Int)
        @PATCH("flights/{id}")
        suspend fun updateFlightFromNetwork(@Path("id") id: Int, @Body flight: Flight): Map<String, String>
        @POST("flights/sync")
        suspend fun synchronizeFlights(@Body flights: List<Flight>)

    }


    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()


    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    val service: Service = retrofit.create(Service::class.java)
}