package com.example.rickmorty.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickmorty.data.common.Either
import com.example.rickmorty.data.common.Failure
import com.example.rickmorty.data.common.makeRequest
import com.example.rickmorty.data.datasource.RickAndMortyApi
import com.example.rickmorty.data.paging.CharactersPagingSource
import com.example.rickmorty.data.mappers.toDomain
import com.example.rickmorty.domain.model.Character
import com.example.rickmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override fun getCharactersPaging(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersPagingSource(api) }
        ).flow
    }
    override suspend fun getCharacterById(id: Int): Either<Failure, Character> =
        makeRequest {
            api.getCharacterById(id).toDomain()
        }
}
