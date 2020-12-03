package com.example.ifly

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FlightModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(Flight: Class<T>): T {
        return FlightViewModel(Application()) as T
    }
}