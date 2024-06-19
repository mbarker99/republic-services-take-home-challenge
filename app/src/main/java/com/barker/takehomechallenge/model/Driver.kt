package com.barker.takehomechallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Driver(
    val id: String,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "index")
    val index: Long = 0L
) {
    fun getLastName() : String {
        return name.split(" ")[1]
    }
}