package mx.com.android.maze.tv.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.android.maze.domain.common.IExceptionHandler
import mx.com.android.maze.domain.di.UseCaseModule
import javax.inject.Singleton

@Module(includes = [UseCaseModule::class])
@InstallIn(SingletonComponent::class)
object AggregatorModule {

    @Provides
    @Singleton
    fun provideExceptionHandle(): IExceptionHandler {
        return IExceptionHandler {
            println(it.message)
        }
    }
}