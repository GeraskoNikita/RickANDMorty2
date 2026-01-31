package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.common.Either
import com.example.rickmorty.data.common.Failure
import com.example.rickmorty.presentation.model.EpisodeUi

interface EpisodeRepository {
    suspend fun getEpisodesUiByIds(ids: List<Int>): Either<Failure, List<EpisodeUi>>
}
