package prueba.movil.prueba.data.model

import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Ana Marin on 26/11/2017.
 */
class Serie(@PrimaryKey var id: Int?,
            var imgPoster: String?,
            var adult: Boolean,
            var overview: String,
            var firstAirDate: String,
            var genrerIds: Array<Int>,
            var title: String,
            var imgBackdrop: String?,
            var voteAverage: Double,
            var popularity: Double
)

{
    @Ignore
    constructor(imgPoster: String, adult: Boolean, overview: String, firstAirDate: String, genrerIds: Array<Int>, title: String, imgBackdrop: String, voteAverage: Double, popularity: Double) : this(
            null, null, adult, overview, firstAirDate, genrerIds, title, null, voteAverage, popularity
    )
}