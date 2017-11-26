package prueba.movil.prueba.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import prueba.movil.prueba.data.model.Movie

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Dao
interface SerieDao {

    @Query("SELECT * FROM serie")
    fun all(): Flowable<List<Movie>>
}