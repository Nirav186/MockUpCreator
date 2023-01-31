package com.example.mockupscreenshots.ui.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mockupscreenshots.data.model.Project
import com.example.mockupscreenshots.domain.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel() {

    fun addProject(project: Project) {
        viewModelScope.launch {
            projectRepository.addNewProject(project)
        }
    }

}