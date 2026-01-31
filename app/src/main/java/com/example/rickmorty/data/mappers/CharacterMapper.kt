package com.example.rickmorty.data.mappers

import com.example.rickmorty.data.model.CharacterDto
import com.example.rickmorty.data.model.LocationDto
import com.example.rickmorty.data.model.OriginDto
import com.example.rickmorty.domain.model.Character
import com.example.rickmorty.domain.model.Place

private fun OriginDto?.toDomainPlace() = Place(
    name = this?.name.orEmpty(),
    url = this?.url.orEmpty()
)

private fun LocationDto?.toDomainPlace() = Place(
    name = this?.name.orEmpty(),
    url = this?.url.orEmpty()
)

fun CharacterDto.toDomain() = Character(
    id = id ?: 0,
    name = name.orEmpty(),
    status = status.orEmpty(),
    species = species.orEmpty(),
    gender = gender.orEmpty(),
    image = image.orEmpty(),
    origin = origin.toDomainPlace(),
    location = location.toDomainPlace(),
    episode = episode.orEmpty().filterNotNull(),
    type = type.orEmpty()
)
