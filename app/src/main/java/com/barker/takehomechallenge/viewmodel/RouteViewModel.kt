package com.barker.takehomechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barker.takehomechallenge.model.Driver
import com.barker.takehomechallenge.model.Route
import com.barker.takehomechallenge.model.response.RoutesResponse
import com.barker.takehomechallenge.repository.DatabaseRepository
import com.barker.takehomechallenge.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject internal constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val mGetRoutesLiveData: MutableLiveData<RoutesResponse> = MutableLiveData()
    val getRoutesLiveData: LiveData<RoutesResponse> = mGetRoutesLiveData

    private val mGetSavedDriversLiveData: MutableLiveData<List<Driver>> = MutableLiveData()
    val getSavedDriversLiveData: LiveData<List<Driver>> = mGetSavedDriversLiveData

    private val mGetSavedRoutesLiveData: MutableLiveData<List<Route>> = MutableLiveData()
    val getSavedRoutesLiveData: LiveData<List<Route>> = mGetSavedRoutesLiveData

    var drivers: List<Driver> = listOf()
    var routes: List<Route> = listOf()
    var isDatabaseEmpty = true

    var selectedDriver: Driver? = null
    var routesForSelectedDriver: Route? = null

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = networkRepository.getRoutes()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    mGetRoutesLiveData.postValue(response.body())
                } else {
                    // handle error
                }
            }
        }
    }

    fun getSavedDrivers() {
        viewModelScope.launch(Dispatchers.IO) {
            val drivers = databaseRepository.getAllDrivers()
            mGetSavedDriversLiveData.postValue(drivers)

            this@RouteViewModel.drivers = drivers
        }
    }

    fun getSavedRoutes() {
        viewModelScope.launch(Dispatchers.IO) {
            val routes = databaseRepository.getAllRoutes()
            mGetSavedRoutesLiveData.postValue(routes)

            this@RouteViewModel.routes = routes
        }
    }

    fun insertData(drivers: List<Driver>, routes: List<Route>) {
        viewModelScope.launch {
            databaseRepository.insertAllDrivers(drivers)
            databaseRepository.insertAllRoutes(routes)
        }
    }
}