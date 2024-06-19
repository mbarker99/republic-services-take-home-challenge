package com.barker.takehomechallenge.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.barker.takehomechallenge.R
import com.barker.takehomechallenge.databinding.FragmentRouteBinding
import com.barker.takehomechallenge.model.Route
import com.barker.takehomechallenge.viewmodel.RouteViewModel

class RouteFragment : Fragment(R.layout.fragment_route) {

    private var binding: FragmentRouteBinding? = null
    private val viewModel: RouteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRouteBinding.bind(view)
        setupUI()
    }

    override fun onStop() {
        super.onStop()
        viewModel.selectedDriver = null
        viewModel.routesForSelectedDriver = null
    }

    private fun setupUI() {
        binding?.apply {
            tvDriverName.text = viewModel.selectedDriver?.name
            tvDriverId.text = "Driver ID: ${viewModel.selectedDriver?.id}"
            findRouteForSelectedDriver()

            tvRouteName.text = viewModel.routesForSelectedDriver?.name
            tvRouteType.text = "Route Type: ${viewModel.routesForSelectedDriver?.type}"
            tvRouteId.text = "Route ID: ${viewModel.routesForSelectedDriver?.id.toString()}"
        }
    }

    private fun findRouteForSelectedDriver() {
        val id = viewModel.selectedDriver?.id?.toInt()
        var selectedRoute: Route? = null
        id?.let {

            //If the driver id is the same as the route id then display the route.
            for (route in viewModel.routes) {
                if (id == route.id) {
                    viewModel.routesForSelectedDriver = route
                    Log.d("Routes", "Driver ID : ${viewModel.selectedDriver!!.id}")
                    Log.d("Routes", viewModel.routesForSelectedDriver.toString())
                    return
                }
            }

            if (id % 2 == 0) {
                //If the driver id is divisible by 2 then show the first R type route.
                selectedRoute = viewModel.routes.first { it.type == "R" }
            } else if (id % 5 == 0) {
                //If the driver id is divisible by 5 then display the second C type route.
                selectedRoute = viewModel.routes.filter { it.type == "C" }[1]
            } else {
                //If the driver id doesn’t meet any of the conditions above, then show the last I type route.
                selectedRoute = viewModel.routes.last { it.type == "I" }
            }
            viewModel.routesForSelectedDriver = selectedRoute


            Log.d("Routes", "Driver ID : ${viewModel.selectedDriver!!.id}")
            Log.d("Routes", viewModel.routesForSelectedDriver.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RouteFragment()
    }


}