package com.example.spaceql

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.rocketreserver.MissionListQuery
import com.example.spaceql.Model.Network.apolloClient
import com.example.spaceql.ViewModel.LaunchViewModel
import com.example.spaceql.ViewModel.MissionViewModel
import com.example.spaceql.databinding.FragmentMissionBinding

class MissionFragment : Fragment() {

    private lateinit var binding: FragmentMissionBinding
    private lateinit var missionViewModel : MissionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.missions.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE


        binding.missions.layoutManager = LinearLayoutManager(requireContext())

        missionViewModel = ViewModelProvider(this).get(MissionViewModel::class.java)
        missionViewModel.getLaunches()
        missionViewModel.missionMutable.observe(viewLifecycleOwner, {
            val adapter = MissionAdapter(it)
            binding.progressBar.visibility = View.GONE
            binding.missions.visibility = View.VISIBLE
            binding.missions.adapter = adapter
        })
    }
}