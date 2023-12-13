package com.D121211026.alquran.data.repository

import com.D121211026.alquran.data.response.GetApiResponse
import com.D121211026.alquran.data.source.remote.ApiService

class QuranRepository (private val apiService: ApiService){

    suspend fun getQuran(): GetApiResponse {
        return apiService.getQuran()
    }
}