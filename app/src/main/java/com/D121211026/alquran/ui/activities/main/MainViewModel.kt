package com.D121211026.alquran.ui.activities.main

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211026.alquran.MyApplication
import com.D121211026.alquran.data.models.Ayah
import com.D121211026.alquran.data.models.Surah
import com.D121211026.alquran.data.repository.QuranRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val surah: List<Surah>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val quranRepository: QuranRepository): ViewModel() {

    // initial state
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getQuran() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = quranRepository.getQuran()
            mainUiState = MainUiState.Success(result.data.surahs.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    // block yg prtama dipanggil ktika ini dibuka
    init {
        getQuran()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val quranRepository = application.container.quranRepository
                MainViewModel(quranRepository)
            }
        }
    }
}