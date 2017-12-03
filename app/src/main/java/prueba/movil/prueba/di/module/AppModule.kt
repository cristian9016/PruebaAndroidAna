package prueba.movil.prueba.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import prueba.movil.prueba.data.db.AppDatabase
import prueba.movil.prueba.data.dao.MovieDao
import prueba.movil.prueba.data.dao.SerieDao
import javax.inject.Singleton

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Module
class AppModule{

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun providesPreferences(application: Application): SharedPreferences =
            application.getSharedPreferences("movie", 0)

    @Singleton
    @Provides
    fun provideDatabase(context: Context) : AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "prueba.db")
                    .fallbackToDestructiveMigration()
                    .build()

    @Singleton
    @Provides
    fun providesMovieDao(appDatabase: AppDatabase): MovieDao =
            appDatabase.movieDao()

    @Singleton
    @Provides
    fun providesSerieDao(appDatabase: AppDatabase): SerieDao =
            appDatabase.serieDao()

}