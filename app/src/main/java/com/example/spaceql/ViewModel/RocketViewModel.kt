package com.example.spaceql.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.rocketreserver.MissionListQuery
import com.example.rocketreserver.RocketListQuery
import com.example.spaceql.Model.Network.apolloClient
import kotlinx.coroutines.launch

class RocketViewModel : ViewModel() {
    val rocketMutable: MutableLiveData<List<RocketListQuery.Launch>> = MutableLiveData()

    fun getLaunches() {
        viewModelScope.launch {
            try {
                val response = apolloClient.query(RocketListQuery()).await()
                rocketMutable.value = response?.data?.launches?.launches?.filterNotNull()
            } catch (e: ApolloException) {
                Log.d("LaunchList", "Failure", e)
                null
            }
        }
    }
}