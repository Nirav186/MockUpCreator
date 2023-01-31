package com.example.mockupscreenshots.ui.project

import com.example.mockupscreenshots.data.model.Project

sealed class ProjectUiState {
    object Loading : ProjectUiState()
    class MyProjectData(val projects: List<Project>?) : ProjectUiState()
    object Error : ProjectUiState()
}