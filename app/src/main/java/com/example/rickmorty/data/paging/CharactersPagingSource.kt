package com.example.rickmorty.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmorty.data.datasource.RickAndMortyApi
import com.example.rickmorty.data.mappers.toDomain
import com.example.rickmorty.domain.model.Character

class CharactersPagingSource(
    private val api: RickAndMortyApi
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = api.getCharacters(page)

            val data = response.results
                .orEmpty()
                .filterNotNull()
                .map { it.toDomain() }

            val totalPages = response.info?.pages ?: 0

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (totalPages != 0 && page >= totalPages) null else page + 1

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
