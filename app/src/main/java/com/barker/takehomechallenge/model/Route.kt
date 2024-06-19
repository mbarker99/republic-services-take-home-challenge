package com.barker.takehomechallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Route(
    val id: Int,
    val name: String,
    val type: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "index")
    val index: Long = 0L
)
