package com.barker.takehomechallenge.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barker.takehomechallenge.R
import com.barker.takehomechallenge.databinding.FragmentDriverBinding
import com.barker.takehomechallenge.ui.adapter.DriverAdapter
import com.barker.takehomechallenge.viewmodel.RouteViewModel
import java.sql.Driver

class DriverFragment : Fragment(R.layout.fragment_driver) {

    private var binding: FragmentDriverBinding? = null
    private val viewModel: RouteViewModel by activityViewModels()

    private val driverAdapter: DriverAdapter by lazy {
        DriverAdapter(viewModel.drivers, object: DriverAdapter.OnDriverClickedListener {
            override fun onDriverClicked(driver: com.barker.takehomechallenge.model.Driver) {
                viewModel.selectedDriver = driver
                findNavController().navigate(R.id.action_driverFragment_to_routeFragment)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDriverBinding.bind(view)
        setupUI()
        setObservers()
    }



    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setupUI() {
        viewModel.getSavedDrivers()
        binding?.apply {
            driverRecycler.adapter = driverAdapter
            driverRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            btnSortDrivers.setOnClickListener {
                if (btnSortDrivers.text == "Last name (Z-A)" || btnSortDrivers.text == "Unsorted") {
                    driverAdapter.sortNameByAsc()
                    btnSortDrivers.text = "Last name (A-Z)"
                } else {
                    driverAdapter.sortNameByDesc()
                    btnSortDrivers.text = "Last name (Z-A)"
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.getSavedDriversLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                viewModel.isDatabaseEmpty = false
                viewModel.drivers = it
                driverAdapter.setData(it)
                viewModel.getSavedRoutes()
            } else {
                viewModel.isDatabaseEmpty = true
                viewModel.getData()
            }
        })

        viewModel.getRoutesLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.insertData(it.drivers, it.routes)
            driverAdapter.setData(it.drivers)
        })

    }


    companion object {
        fun newInstance() = DriverFragment()
    }
}