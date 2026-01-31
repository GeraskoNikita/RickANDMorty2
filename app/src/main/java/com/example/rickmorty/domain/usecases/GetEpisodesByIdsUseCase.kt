package com.example.rickmorty.domain.usecases

import com.example.rickmorty.data.common.Either
import com.example.rickmorty.data.common.Failure
import com.example.rickmorty.domain.repository.EpisodeRepository
import com.example.rickmorty.presentation.model.EpisodeUi

class GetEpisodesByIdsUseCase(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(ids: List<Int>): Either<Failure, List<EpisodeUi>> =
        repository.getEpisodesUiByIds(ids)
}
