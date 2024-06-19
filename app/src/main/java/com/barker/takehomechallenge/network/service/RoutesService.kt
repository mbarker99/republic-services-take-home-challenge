package com.barker.takehomechallenge.network.service

import com.barker.takehomechallenge.model.response.RoutesResponse
import retrofit2.Response
import retrofit2.http.GET

interface RoutesService {
    companion object {

    }

    @GET("data/")
    suspend fun getRoutes() : Response<RoutesResponse>
}