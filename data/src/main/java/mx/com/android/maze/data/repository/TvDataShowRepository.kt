package mx.com.android.maze.data.repository

import mx.com.android.maze.domain.model.Person
import mx.com.android.maze.domain.model.Show
import mx.com.android.maze.domain.repository.TvShowRepository

class TvDataShowRepository(
    private val showTvDataSource: ShowTvDataSource
) : TvShowRepository {

    override suspend fun getTvShows(date: String): List<Show> {
        return showTvDataSource.getTvShows(date)
    }

    override suspend fun findTvShowByQuery(query: String): List<Show> {
        return showTvDataSource.findTvShowByQuery(query)
    }

    override suspend fun getTvShowById(showId: String): Show {
        return showTvDataSource.getTvShowById(showId)
    }

    override suspend fun getCastFromTvShow(showId: String): List<Person> {
        return showTvDataSource.getCastFromTvShow(showId)
    }

}