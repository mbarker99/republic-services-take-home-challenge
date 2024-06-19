package com.barker.takehomechallenge.model.response

import com.barker.takehomechallenge.model.Driver
import com.barker.takehomechallenge.model.Route

data class RoutesResponse(
    val drivers: List<Driver>,
    val routes: List<Route>
)