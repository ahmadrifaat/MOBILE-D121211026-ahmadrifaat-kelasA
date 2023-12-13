package com.D121211026.alquran.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Data(
    val edition: Edition,
    val surahs: List<Surah>
) : Parcelable