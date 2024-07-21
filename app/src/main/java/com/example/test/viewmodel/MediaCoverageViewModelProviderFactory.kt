package com.example.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.repository.MediaCoverageRepository

class MediaCoverageViewModelProviderFactory(private val repository: MediaCoverageRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MediaCoverageViewModel::class.java)){
            return MediaCoverageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }
}