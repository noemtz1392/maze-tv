package mx.com.android.maze.data.source.remote

import mx.com.android.maze.data.source.remote.response.ApiCast
import mx.com.android.maze.data.source.remote.response.ApiResponse
import mx.com.android.maze.data.source.remote.response.ApiShow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MazeApi {

    @GET("/schedule")
    suspend fun getTvShows(
        @Query("country") country: String,
        @Query("date") date: String
    ): Response<List<ApiResponse>>

    @GET("/search/shows")
    suspend fun findTvShowByQuery(
        @Query("q") query: String
    ): Response<List<ApiResponse>>

    @GET("/shows/{showId}")
    suspend fun getTvShowById(
        @Path("showId") showId: String
    ): Response<ApiShow>

    @GET("/shows/{showId}/cast")
    suspend fun getCastFromTvShow(
        @Path("showId") showId: String
    ): Response<List<ApiCast>>
}