package com.example.agora.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agora.ui.services.AgoraApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    private val _humidity = MutableLiveData<String>()

    val humidity: LiveData<String> = _humidity

    private val _temp = MutableLiveData<String>()

    val temp: LiveData<String> = _temp
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val result = AgoraApi.retrofitService.getProperties("35","139","4727bdf007debf7a685d10c3659777cc","metric").await()
                _humidity.value = result.main.humidity
                _temp.value = result.main.temp
                _status.value = result.base
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}