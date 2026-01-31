package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.common.Either
import com.example.rickmorty.data.common.Failure
import com.example.rickmorty.domain.model.Character
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharactersPaging(): Flow<PagingData<Character>>

    suspend fun getCharacterById(id: Int): Either<Failure, Character>
}
