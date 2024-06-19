package com.barker.takehomechallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barker.takehomechallenge.databinding.ItemViewDriverBinding
import com.barker.takehomechallenge.model.Driver
import java.util.Collections


class DriverAdapter(
    private var drivers: List<Driver>,
    private var listener: OnDriverClickedListener
) :
    RecyclerView.Adapter<DriverAdapter.DriverViewHolder>() {

    interface OnDriverClickedListener {
        fun onDriverClicked(driver: Driver)
    }

    inner class DriverViewHolder(private val binding: ItemViewDriverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(driver: Driver) {
            binding.apply {
                tvDriverName.text = driver.name
                tvDriverId.text = "ID: ${driver.id}"

                root.setOnClickListener {
                    listener.onDriverClicked(driver)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewDriverBinding.inflate(layoutInflater, parent, false)
        return DriverViewHolder(binding)
    }

    override fun getItemCount(): Int = drivers.size

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.bind(drivers[position])
    }

    fun setData(drivers: List<Driver>) {
        this.drivers = drivers
        notifyDataSetChanged()
    }

    fun sortNameByAsc() {
        val comparator: Comparator<Driver> =
            Comparator<Driver> { object1, object2 ->
                object1.getLastName().compareTo(object2.getLastName(), true)
            }
        Collections.sort(drivers, comparator)
        notifyDataSetChanged()
    }

    fun sortNameByDesc() {
        val comparator: Comparator<Driver> =
            Comparator<Driver> { object1, object2 ->
                object2.getLastName().compareTo(object1.getLastName())
            }
        Collections.sort(drivers, comparator)
        notifyDataSetChanged()
    }
}