package prueba.movil.prueba.data.dao
/**
 * Created by Ana Marin on 26/11/2017.
 */
import android.arch.persistence.room.*
import io.reactivex.Flowable
import prueba.movil.prueba.data.model.Movie

/**
 * Created by Ana Marin on 13/10/2017.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun all(): Flowable<List<Movie>>
}