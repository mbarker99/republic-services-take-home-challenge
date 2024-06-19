package com.barker.takehomechallenge.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.barker.takehomechallenge.db.RouteDatabase
import com.barker.takehomechallenge.model.Driver
import com.barker.takehomechallenge.model.Route
import com.barker.takehomechallenge.util.Constants
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val db: RouteDatabase) {

    suspend fun getAllDrivers(): List<Driver> = db.routeDao().getAllDrivers()

    suspend fun insertAllDrivers(drivers: List<Driver>)  = db.routeDao().insertAllDrivers(drivers)

    suspend fun getAllRoutes(): List<Route> = db.routeDao().getAllRoutes()

    suspend fun insertAllRoutes(routes: List<Route>) = db.routeDao().insertAllRoutes(routes)

}