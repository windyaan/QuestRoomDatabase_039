package com.example.project.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import com.example.project.Repository.RepositoryMhs
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class HomeMhsViewModel (
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){

    val homeUiState: StateFlow<HomeUiState> = repositoryMhs.getAllMhs()
        .filterNotNull()
        .map {
            HomeUiState (
                listMhs = it.toList(),
                isLoading = false,
            )
        }


}