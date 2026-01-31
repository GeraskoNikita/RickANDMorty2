package com.example.rickmorty.presentation.model

import com.example.rickmorty.domain.model.Character

data class CharacterDetailUi(
    val character: Character,
    val episodes: List<EpisodeUi>
) {
    val firstSeenName: String get() = episodes.firstOrNull()?.name ?: "—"
}