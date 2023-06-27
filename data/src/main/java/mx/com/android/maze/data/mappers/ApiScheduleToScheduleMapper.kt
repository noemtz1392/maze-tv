package mx.com.android.maze.data.mappers

import mx.com.android.maze.data.source.remote.response.ApiSchedule
import mx.com.android.maze.domain.common.Mapper
import mx.com.android.maze.domain.model.Schedule
import javax.inject.Inject

class ApiScheduleToScheduleMapper
@Inject constructor() : Mapper<ApiSchedule, Schedule>() {

    override fun mapFrom(from: ApiSchedule): Schedule {
        return Schedule(from.time, from.days.joinToString(", "))
    }
}