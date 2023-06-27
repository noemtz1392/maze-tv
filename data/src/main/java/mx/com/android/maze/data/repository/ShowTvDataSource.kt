package mx.com.android.maze.data.repository

import mx.com.android.maze.domain.model.Person
import mx.com.android.maze.domain.model.Show

interface ShowTvDataSource {
    suspend fun getTvShows(date: String): List<Show>

    suspend fun findTvShowByQuery(query: String): List<Show>

    suspend fun getTvShowById(showId: String): Show

    suspend fun getCastFromTvShow(showId: String): List<Person>
}