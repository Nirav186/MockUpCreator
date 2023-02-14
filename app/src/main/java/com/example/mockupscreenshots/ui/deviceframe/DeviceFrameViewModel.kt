package com.example.mockupscreenshots.ui.deviceframe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mockupscreenshots.core.Resource
import com.example.mockupscreenshots.domain.usecase.UseCaseGetHomeFramesFromAsset
import com.example.mockupscreenshots.domain.usecase.UseCaseLoadJsonFromAsset
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceFrameViewModel @Inject constructor(
    private val useCaseLoadJsonFromAsset: UseCaseLoadJsonFromAsset,
    private val useCaseGetHomeFramesFromAsset: UseCaseGetHomeFramesFromAsset
) : ViewModel() {

    var state by mutableStateOf(DeviceFrameState())

    init {
        getFrames()
        getHomeFrames()
    }

    private fun getHomeFrames() {
        viewModelScope.launch {
            useCaseLoadJsonFromAsset.action(Unit).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { frameItems ->
                            state.frameItems = frameItems
                        }
                    }
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }

    private fun getFrames() {
        viewModelScope.launch {
            useCaseGetHomeFramesFromAsset.action(Unit).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { frameItems ->
                            state.homeFrames = frameItems
                        }
                    }
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }

}