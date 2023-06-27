package mx.com.android.maze.data.mappers

import mx.com.android.maze.data.source.remote.response.ApiShow
import mx.com.android.maze.domain.common.Mapper
import mx.com.android.maze.domain.model.Show
import javax.inject.Inject

class ApiShowToShowMapper
@Inject constructor(
    private val apiScheduleToScheduleMapper: ApiScheduleToScheduleMapper
) : Mapper<ApiShow, Show>() {

    override fun mapFrom(from: ApiShow): Show = Show(
        id = from.id.toString(),
        name = from.name,
        networkName = from.network?.name,
        airDate = null,
        airTime = null,
        rating = from.rating?.average,
        summary = from.summary,
        officialSite = from.url,
        genres = from.genres?.joinToString(", "),
        imageUrl = from.image.medium,
        schedule = apiScheduleToScheduleMapper.mapFrom(from.schedule)
    )
}