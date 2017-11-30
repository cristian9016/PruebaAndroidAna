package prueba.movil.prueba.data.dao

/**
 * Created by Ana Marin on 26/11/2017.
 */
import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import prueba.movil.prueba.data.model.Movie

/**
 * Created by Ana Marin on 13/10/2017.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE category = :category")
    fun lastest(category: Int): Single<List<Movie>>

    @Query("DELETE FROM movie WHERE category = :category")
    fun removeByCategory(category: Int)

    @Insert
    fun insert(movies: List<Movie>)
}