package com.example.rickmorty.data.repository


import android.util.Log
import com.example.rickmorty.data.common.makeRequest
import com.example.rickmorty.data.datasource.RickAndMortyApi
import com.example.rickmorty.presentation.model.EpisodeUi

class EpisodeRepositoryImpl(
    private val api: RickAndMortyApi
) : com.example.rickmorty.domain.repository.EpisodeRepository {

    override suspend fun getEpisodesUiByIds(ids: List<Int>) =
        makeRequest {
            if (ids.isEmpty()) return@makeRequest emptyList()

            val dtos = if (ids.size == 1) {
                listOf(api.getEpisodeById(ids.first()))
            } else {
                api.getEpisodesByIds(ids.joinToString(","))
            }

            dtos.map { dto ->
                val (season, ep) = parseSeasonEpisode(dto.episodeCode.orEmpty())

                Log.d(
                    "EPISODE",
                    "code=${dto.episodeCode}, season=$season, ep=$ep, date=${dto.airDate}"
                )

                EpisodeUi(
                    season = season,
                    episodeNumber = ep,
                    name = dto.name.orEmpty(),
                    airDate = dto.airDate.orEmpty()
                )
            }
        }



    private fun parseSeasonEpisode(code: String): Pair<Int, Int> {
        if (code.isBlank()) return 0 to 0

        val season = code.substringAfter("S", "")
            .substringBefore("E", "")
            .toIntOrNull() ?: 0

        val episode = code.substringAfter("E", "")
            .toIntOrNull() ?: 0

        return season to episode
    }

}
