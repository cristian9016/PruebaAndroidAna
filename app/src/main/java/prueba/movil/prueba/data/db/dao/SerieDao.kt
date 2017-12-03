package prueba.movil.prueba.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import prueba.movil.prueba.data.model.Serie

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Dao
interface SerieDao {

    @Query("SELECT * FROM serie WHERE category = :category")
    fun lastest(category: Int): Single<List<Serie>>

    @Query("DELETE FROM serie WHERE category = :category")
    fun removeByCategory(category: Int)

    @Insert
    fun insert(series: List<Serie>)
}