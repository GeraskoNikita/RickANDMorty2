package com.example.rickmorty.data.datasource

import com.example.rickmorty.data.model.CharacterDto
import com.example.rickmorty.data.model.CharactersResponseDto
import com.example.rickmorty.data.model.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersResponseDto

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): EpisodeDto
    @GET("episode/{ids}")
    suspend fun getEpisodesByIds(@Path("ids") ids: String): List<EpisodeDto>


}
