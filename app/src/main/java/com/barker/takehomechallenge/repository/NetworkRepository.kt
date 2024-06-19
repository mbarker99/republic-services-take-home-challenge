package com.barker.takehomechallenge.repository

import com.barker.takehomechallenge.network.service.RoutesService
import javax.inject.Inject

class NetworkRepository @Inject internal constructor(
    private val routeService: RoutesService
) {
    suspend fun getRoutes() = routeService.getRoutes()
}