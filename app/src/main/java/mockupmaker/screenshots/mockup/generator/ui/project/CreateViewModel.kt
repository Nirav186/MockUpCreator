package mockupmaker.screenshots.mockup.generator.ui.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor() : ViewModel() {

    var screenshots = mutableStateListOf<String>()
    var projectName by  mutableStateOf("")
    var projectDes by  mutableStateOf("")
    var device by  mutableStateOf("Android")
    var projectId by  mutableStateOf(0L)

}