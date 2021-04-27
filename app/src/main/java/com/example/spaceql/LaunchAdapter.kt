package com.example.spaceql

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.rocketreserver.LaunchListQuery
import com.example.spaceql.databinding.MissionItemBinding

class LaunchAdapter(val launches: List<LaunchListQuery.Launch>) :
    RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    class ViewHolder(val binding: MissionItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return launches.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MissionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launch = launches.get(position)
        holder.binding.mission.text = launch.mission?.name
        holder.binding.site.text = launch.site ?: ""
        holder.binding.profilePic.load(launch.mission?.missionPatch) {
            placeholder(R.drawable.ic_placeholder)
        }
    }
}