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
import com.example.rocketreserver.LaunchListQuery
import com.example.spaceql.Model.Network.apolloClient
import com.example.spaceql.ViewModel.LaunchViewModel
import com.example.spaceql.databinding.FragmentLaunchBinding

class LaunchFragment : Fragment() {

    private lateinit var binding: FragmentLaunchBinding
    private lateinit var launchViewModel: LaunchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.launches.visibility = View.GONE
        binding.launchProgressBar.visibility = View.VISIBLE


        binding.launches.layoutManager = LinearLayoutManager(requireContext())

        launchViewModel = ViewModelProvider(this).get(LaunchViewModel::class.java)
        launchViewModel.getLaunches()
        launchViewModel.launchMutable.observe(viewLifecycleOwner, {
            val adapter = LaunchAdapter(it)
            binding.launchProgressBar.visibility = View.GONE
            binding.launches.visibility = View.VISIBLE
            binding.launches.adapter = adapter
        })
    }
}


