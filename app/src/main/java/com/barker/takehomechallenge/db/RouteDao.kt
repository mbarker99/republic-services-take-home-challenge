package com.barker.takehomechallenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.barker.takehomechallenge.model.Driver
import com.barker.takehomechallenge.model.Route
import com.barker.takehomechallenge.util.Constants

@Dao
interface RouteDao {

    @Query("SELECT * FROM ${Constants.DB_DRIVER_TABLE_NAME}")
    suspend fun getAllDrivers(): List<Driver>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDrivers(drivers: List<Driver>)

    @Delete
    suspend fun deleteDriver(driver: Driver)

    @Update
    suspend fun updateDriver(driver: Driver)

    @Query("SELECT * FROM ${Constants.DB_ROUTE_TABLE_NAME}")
    suspend fun getAllRoutes(): List<Route>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutes(routes: List<Route>)

    @Delete
    suspend fun deleteRoute(route: Route)

    @Update
    suspend fun updateRoute(route: Route)


}