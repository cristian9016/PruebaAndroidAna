package prueba.movil.prueba.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Entity
class Serie: Item() {

    @PrimaryKey
    var id:Long? = null

    @SerializedName("first_air_date")
    lateinit var firstAirDate:String

    override fun getType(): Int = TYPE_SERIE
}