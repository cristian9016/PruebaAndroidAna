package prueba.movil.prueba.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import prueba.movil.prueba.data.dao.MovieDao
import prueba.movil.prueba.data.dao.SerieDao
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.data.model.Serie

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Database(entities = arrayOf(Movie::class, Serie::class), version = 3)

abstract class AppDatabase: RoomDatabase(){
    abstract fun movieDao(): MovieDao //Proveo el acceso a mi Dao
    abstract fun serieDao(): SerieDao

}

