package com.D121211026.alquran.data

import com.D121211026.alquran.data.repository.QuranRepository
import com.D121211026.alquran.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val quranRepository : QuranRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://api.alquran.cloud"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val quranRepository: QuranRepository
        get() = QuranRepository(retrofitService)

}