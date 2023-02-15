package com.mobileappxperts.mockupgenerator.mockupmaker.ui.project

import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project

sealed class ProjectUiState {
    object Loading : ProjectUiState()
    class MyProjectData(val projects: List<Project>?) : ProjectUiState()
    object Error : ProjectUiState()
}