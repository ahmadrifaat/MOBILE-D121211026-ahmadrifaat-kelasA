package com.D121211026.alquran.data.models

import android.os.Parcelable
import com.D121211026.alquran.data.models.Ayah
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Surah(
    val ayahs: List<Ayah>,
    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    val number: Int,
    val revelationType: String
) : Parcelable