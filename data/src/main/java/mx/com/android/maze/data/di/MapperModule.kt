package mx.com.android.maze.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.android.maze.data.mappers.ApiCastToPersonMapper
import mx.com.android.maze.data.mappers.ApiScheduleToScheduleMapper
import mx.com.android.maze.data.mappers.ApiShowToShowMapper

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideApiCastToPersonMapper() = ApiCastToPersonMapper()

    @Provides
    fun provideApiShowToShowMapper(apiScheduleToScheduleMapper: ApiScheduleToScheduleMapper) =
        ApiShowToShowMapper(apiScheduleToScheduleMapper)

    @Provides
    fun provideApiScheduleToScheduleMapper() = ApiScheduleToScheduleMapper()
}