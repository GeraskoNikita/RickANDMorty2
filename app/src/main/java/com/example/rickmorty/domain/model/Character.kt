package com.example.rickmorty.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,

    val origin: Place,
    val location: Place,
    val episode: List<String>,
    val type: String
)
