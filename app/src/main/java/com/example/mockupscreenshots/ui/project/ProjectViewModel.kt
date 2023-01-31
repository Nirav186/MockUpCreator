package com.example.mockupscreenshots.ui.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mockupscreenshots.data.model.Project
import com.example.mockupscreenshots.domain.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<ProjectUiState>(ProjectUiState.Loading)
    val uiState = _uiState.asStateFlow()
    init {
        getMyProjects()
    }

    fun addProject(project: Project) {
        viewModelScope.launch {
            projectRepository.addNewProject(project)
        }
    }

    private fun getMyProjects() {
        viewModelScope.launch {
            val list = projectRepository.getMyProjects()
            _uiState.value = ProjectUiState.MyProjectData(projects = list)
        }
    }

}