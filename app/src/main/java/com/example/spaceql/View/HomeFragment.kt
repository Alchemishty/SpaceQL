package com.example.spaceql

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.rocketreserver.LaunchListQuery
import com.example.spaceql.Model.Network.apolloClient
import com.example.spaceql.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            binding.launches.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            val response = try {
                apolloClient.query(LaunchListQuery()).await()
            } catch (e: ApolloException) {
                Log.d("LaunchList", "Failure", e)
                null
            }

            val launches = response?.data?.launches?.launches?.filterNotNull()
            if (launches != null && !response.hasErrors()) {
                val adapter = LaunchAdapter(launches)
                binding.progressBar.visibility = View.GONE
                binding.launches.visibility = View.VISIBLE
                binding.launches.layoutManager = LinearLayoutManager(requireContext())
                binding.launches.adapter = adapter
            }
        }
    }
}