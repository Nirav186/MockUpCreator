package com.example.mockupscreenshots.ui.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor() : ViewModel() {

    val screenshots = mutableStateOf(mutableListOf<String>())
    var projectName by  mutableStateOf("")
    var projectDes by  mutableStateOf("")
    var device by  mutableStateOf("Android")

}