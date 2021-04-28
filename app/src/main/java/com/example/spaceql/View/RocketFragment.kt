package com.example.spaceql.View

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
import com.example.rocketreserver.RocketListQuery
import com.example.spaceql.MissionAdapter
import com.example.spaceql.Model.Network.apolloClient
import com.example.spaceql.RocketAdapter
import com.example.spaceql.ViewModel.MissionViewModel
import com.example.spaceql.ViewModel.RocketViewModel
import com.example.spaceql.databinding.FragmentRocketBinding

class RocketFragment : Fragment() {

    private lateinit var binding: FragmentRocketBinding
    private lateinit var rocketViewModel: RocketViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rockets.visibility = View.GONE
        binding.rocketProgressBar.visibility = View.VISIBLE


        binding.rockets.layoutManager = LinearLayoutManager(requireContext())

        rocketViewModel = ViewModelProvider(this).get(RocketViewModel::class.java)
        rocketViewModel.getLaunches()
        rocketViewModel.rocketMutable.observe(viewLifecycleOwner, {
            val adapter = RocketAdapter(it)
            binding.rocketProgressBar.visibility = View.GONE
            binding.rockets.visibility = View.VISIBLE
            binding.rockets.adapter = adapter
        })
    }
}

