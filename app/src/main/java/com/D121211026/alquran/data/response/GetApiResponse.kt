package com.D121211026.alquran.data.response

import com.D121211026.alquran.data.models.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetApiResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("`data`")
    val `data`: Data,
    @SerialName("status")
    val status: String
)