package com.example.mockupscreenshots.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mockupscreenshots.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceFrameViewModel @Inject constructor(
    private val useCaseLoadJsonFromAsset: UseCaseLoadJsonFromAsset
) : ViewModel() {

    var state by mutableStateOf(DeviceFrameState())

    init {
        getBankDataMap()
    }

    private fun getBankDataMap() {
        viewModelScope.launch {
            useCaseLoadJsonFromAsset.action(Unit).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { bankData ->
                            state.frameItems = bankData
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