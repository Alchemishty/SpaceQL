package com.example.spaceql.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.rocketreserver.MissionListQuery
import com.example.spaceql.Model.Network.apolloClient
import kotlinx.coroutines.launch

class MissionViewModel : ViewModel() {
    val missionMutable: MutableLiveData<List<MissionListQuery.Launch>> = MutableLiveData()

    fun getLaunches() {
        viewModelScope.launch {
            try {
                val response = apolloClient.query(MissionListQuery()).await()
                missionMutable.value = response?.data?.launches?.launches?.filterNotNull()
            } catch (e: ApolloException) {
                Log.d("LaunchList", "Failure", e)
                null
            }
        }
    }
}