package com.example.rickmorty.di

import com.example.rickmorty.data.datasource.RickAndMortyApi
import com.example.rickmorty.data.datasource.createHttpLoggingInterceptor
import com.example.rickmorty.data.datasource.createOkHttpClient
import com.example.rickmorty.data.datasource.createRetrofit
import com.example.rickmorty.data.repository.CharacterRepositoryImpl
import com.example.rickmorty.data.repository.EpisodeRepositoryImpl
import com.example.rickmorty.domain.repository.CharacterRepository
import com.example.rickmorty.domain.repository.EpisodeRepository
import com.example.rickmorty.domain.usecases.GetCharacterByIdUseCase
import com.example.rickmorty.domain.usecases.GetCharactersPagingUseCase
import com.example.rickmorty.domain.usecases.GetEpisodesByIdsUseCase
import com.example.rickmorty.presentation.characters.CharactersViewModel

import com.example.rickmorty.presentation.detail.CharacterDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit




val appModule = module {

    single { createHttpLoggingInterceptor() }
    single { createOkHttpClient(get()) }
    single { createRetrofit(get()) }
    single<RickAndMortyApi> { get<Retrofit>().create(RickAndMortyApi::class.java) }

    single<CharacterRepository> { CharacterRepositoryImpl(api = get<RickAndMortyApi>()) }
    single<EpisodeRepository> { EpisodeRepositoryImpl(api = get<RickAndMortyApi>()) }


    factory { GetCharacterByIdUseCase(get()) }
    factory { GetEpisodesByIdsUseCase(get()) }

    factory { GetCharactersPagingUseCase(get()) }
    viewModel { CharactersViewModel(get()) }

    viewModel { CharactersViewModel(get()) }
    viewModel {(characterId: Int) ->
        CharacterDetailViewModel( getCharacterByIdUseCase=get(), getEpisodesByIdsUseCase = get(), characterId = characterId ) }
}
