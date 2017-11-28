package prueba.movil.prueba.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Entity
class Movie: Item() {

    @PrimaryKey
    var id:Long? = null
    @SerializedName("release_date")
    lateinit var releaseDate:String
    var adult:Boolean = false

    override fun getType(): Int = TYPE_MOVIE
}