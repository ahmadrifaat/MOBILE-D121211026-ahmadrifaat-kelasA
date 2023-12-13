package com.D121211026.alquran.data.source.remote

import com.D121211026.alquran.data.response.GetApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v1/quran/quran-uthmani")
    suspend fun getQuran(
    ) : GetApiResponse
}