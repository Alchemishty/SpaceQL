package com.example.spaceql.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.launch
import com.apollographql.apollo.exception.ApolloException
import com.example.rocketreserver.LaunchListQuery
import com.example.spaceql.Model.Network.apolloClient

class LaunchViewModel : ViewModel() {
    val launchMutable: MutableLiveData<List<LaunchListQuery.Launch>> = MutableLiveData()

    fun getLaunches() {
        viewModelScope.launch{
            try {
                val response = apolloClient.query(LaunchListQuery()).await()
                launchMutable.value = response?.data?.launches?.launches?.filterNotNull()
            } catch (e: ApolloException) {
                Log.d("LaunchList", "Failure", e)
                null
            }
        }
    }
}