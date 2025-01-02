package mockupmaker.screenshots.mockup.generator.ui.project

import mockupmaker.screenshots.mockup.generator.data.model.Project

sealed class ProjectUiState {
    object Loading : ProjectUiState()
    class MyProjectData(val projects: List<Project>?) : ProjectUiState()
    object Error : ProjectUiState()
}