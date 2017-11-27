package prueba.movil.prueba.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Entity
open abstract class Item(@PrimaryKey var id: Int?,
           var imgPoster: String?,
           var overview: String,
           var date: String,
           var genrerIds: Array<Int>,
           var title: String,
           var imgBackdrop: String?,
           var voteAverage: Double,
           var popularity: Double
)
{
    @Ignore
    constructor(imgPoster: String, overview: String, date: String, genrerIds: Array<Int>, title: String, imgBackdrop: String, voteAverage: Double, type: Int, popularity: Double) : this(
            null, null, overview, date, genrerIds, title, null, voteAverage, popularity
    )

    companion object {
        val TYPE_SERIE: Int  = 0
        val TYPE_MOVIE: Int = 1
    }
}
