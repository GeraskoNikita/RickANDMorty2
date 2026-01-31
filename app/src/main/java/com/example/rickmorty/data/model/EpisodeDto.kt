package com.example.rickmorty.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("air_date")
    val airDate: String? = null,

    @SerializedName("episode")
    val episodeCode: String? = null
)
