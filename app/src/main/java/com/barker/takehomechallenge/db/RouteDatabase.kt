package com.barker.takehomechallenge.db

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.barker.takehomechallenge.model.Driver
import com.barker.takehomechallenge.model.Route

@Database(entities = [Driver::class, Route::class], version = 1)
abstract class RouteDatabase : RoomDatabase() {
    abstract fun routeDao() : RouteDao
}