package com.example.spaceql

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketreserver.LaunchListQuery
import com.example.spaceql.databinding.LaunchItemBinding

class LaunchAdapter(val launches: List<LaunchListQuery.Launch>) :
    RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    class ViewHolder(val binding: LaunchItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return launches.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LaunchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        println("Launch item called")
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launch = launches.get(position)
        holder.binding.site.text = launch.site ?: ""
        holder.binding.id.text = launch.id
        }
    }