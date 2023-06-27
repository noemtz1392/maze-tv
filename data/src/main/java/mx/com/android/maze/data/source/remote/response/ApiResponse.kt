package mx.com.android.maze.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import mx.com.android.maze.domain.model.Show

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "airdate") val airDate: String?,
    @Json(name = "airtime") val airTime: String?,
    val summary: String?,
    val show: ApiShow
)

@JsonClass(generateAdapter = true)
data class ApiShow(
    var id: Long,
    val url: String,
    val name: String,
    val schedule: ApiSchedule,
    val network: ApiNetwork?,
    val image: ApiImage,
    val rating: ApiRating?,
    val summary: String?,
    val genres: List<String>?
)

@JsonClass(generateAdapter = true)
data class ApiImage(
    val medium: String,
    val original: String
)

@JsonClass(generateAdapter = true)
data class ApiNetwork(
    val name: String
)

@JsonClass(generateAdapter = true)
data class ApiRating(
    val average: String?
)

@JsonClass(generateAdapter = true)
data class ApiSchedule(
    val time: String,
    val days: List<String>
)

@JsonClass(generateAdapter = true)
data class ApiCast(
    val person: ApiPerson
)

@JsonClass(generateAdapter = true)
data class ApiPerson(
    val name: String,
    val image: ApiImage
)

fun ApiResponse.toShowDomain(): Show {
    return Show(
        id = show.id.toString(),
        name = show.name,
        networkName = show.network?.name,
        airDate = airDate,
        airTime = airTime,
        rating = show.rating?.average,
        summary = summary,
        officialSite = show.url,
        genres = show.genres?.joinToString(", "),
        imageUrl = show.image.medium,
        schedule = null

    )
}