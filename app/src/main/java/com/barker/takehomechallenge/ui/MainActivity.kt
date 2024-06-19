package com.barker.takehomechallenge.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import com.barker.takehomechallenge.databinding.ActivityMainBinding
import com.barker.takehomechallenge.model.response.RoutesResponse
import com.barker.takehomechallenge.repository.DatabaseRepository
import com.barker.takehomechallenge.repository.NetworkRepository
import com.barker.takehomechallenge.viewmodel.RouteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var networkRepository: NetworkRepository
    @Inject
    lateinit var databaseRepository: DatabaseRepository

    private val viewModel: RouteViewModel by viewModels {
        viewModelFactory { RouteViewModel(networkRepository, databaseRepository) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}