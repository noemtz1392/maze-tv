package mx.com.android.maze.data.source.remote

import mx.com.android.maze.data.NetworkResult
import mx.com.android.maze.data.mappers.ApiCastToPersonMapper
import mx.com.android.maze.data.mappers.ApiShowToShowMapper
import mx.com.android.maze.data.repository.ShowTvDataSource
import mx.com.android.maze.data.safeApiCall
import mx.com.android.maze.data.source.remote.response.toShowDomain
import mx.com.android.maze.domain.model.Person
import mx.com.android.maze.domain.model.Show
import java.io.IOException
import javax.inject.Inject

class RetrofitTvShowDataSource
@Inject constructor(
    private val api: MazeApi,
    private val apiCastToPersonMapper: ApiCastToPersonMapper,
    private val apiShowToShowMapper: ApiShowToShowMapper
) : ShowTvDataSource {

    override suspend fun getTvShows(date: String): List<Show> {
        val response = safeApiCall {
            api.getTvShows("US", date)
        }
        return when (response) {
            is NetworkResult.Error -> throw IOException()
            is NetworkResult.Failure -> throw IOException()
            is NetworkResult.Success -> response.data.map { it.toShowDomain() }
        }
    }

    override suspend fun findTvShowByQuery(query: String): List<Show> {
        val response = safeApiCall {
            api.findTvShowByQuery(query)
        }

        return when (response) {
            is NetworkResult.Error -> throw IOException()
            is NetworkResult.Failure -> throw IOException()
            is NetworkResult.Success -> response.data.map { it.toShowDomain() }
        }
    }

    override suspend fun getTvShowById(showId: String): Show {
        val response = safeApiCall {
            api.getTvShowById(showId)
        }
        return when (response) {
            is NetworkResult.Error -> throw IOException()
            is NetworkResult.Failure -> throw IOException()
            is NetworkResult.Success -> apiShowToShowMapper.mapFrom(response.data)
        }
    }

    override suspend fun getCastFromTvShow(showId: String): List<Person> {
        val response = safeApiCall {
            api.getCastFromTvShow(showId)
        }
        return when (response) {
            is NetworkResult.Error -> throw Exception()
            is NetworkResult.Failure -> throw IllegalStateException()
            is NetworkResult.Success -> response.data.map {
                apiCastToPersonMapper.mapFrom(it)

            }
        }
    }
}