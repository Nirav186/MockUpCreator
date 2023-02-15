package com.mobileappxperts.mockupgenerator.mockupmaker.ui.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.mobileappxperts.mockupgenerator.mockupmaker.domain.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<ProjectUiState>(ProjectUiState.Loading)
    val uiState = _uiState.asStateFlow()

    var project by mutableStateOf(Project())
    var selectedProjects = mutableStateListOf<Project>()

    init {
        getMyProjects()
    }

    fun addProject(project: Project) {
        viewModelScope.launch {
            projectRepository.addNewProject(project)
        }
    }

    fun getProjectById(projectId: Long) {
        viewModelScope.launch {
            projectRepository.getProjectById(projectId).collectLatest {
                project = it
            }
        }
    }

    private fun getMyProjects() {
        viewModelScope.launch {
            projectRepository.getMyProjects().collectLatest {
                _uiState.value = ProjectUiState.MyProjectData(projects = it)
            }
        }
    }

    fun deleteSelectedProjects() {
        viewModelScope.launch(Dispatchers.IO) {
            projectRepository.deleteProjects(selectedProjects.map { it.projectId } as ArrayList<Long>)
            selectedProjects.clear()
        }
    }

}