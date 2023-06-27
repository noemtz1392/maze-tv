package mx.com.android.maze.data.mappers

import mx.com.android.maze.data.source.remote.response.ApiCast
import mx.com.android.maze.domain.common.Mapper
import mx.com.android.maze.domain.model.Person
import javax.inject.Inject

class ApiCastToPersonMapper
@Inject constructor() : Mapper<ApiCast, Person>() {

    override fun mapFrom(from: ApiCast): Person = Person(
        name = from.person.name,
        photoUrl = from.person.image.medium
    )
}