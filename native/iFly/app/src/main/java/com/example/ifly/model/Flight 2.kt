package com.example.ifly.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "dateTime") var datetime: String,
    @ColumnInfo(name = "departure") var departure: String,
    @ColumnInfo(name = "arrival") var arrival: String,
    @ColumnInfo(name = "code") var code: String,
    var details: String
)