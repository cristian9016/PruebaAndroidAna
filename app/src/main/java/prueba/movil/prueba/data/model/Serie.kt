package prueba.movil.prueba.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Entity
class Serie(id: Int, imgPoster: String, overview: String, date: String, genrerIds: Array<Int>, title: String,
            imgBackdrop: String, voteAverage: Double, popularity: Double)
    : Item(id, imgPoster, overview, date, genrerIds, title, imgBackdrop, voteAverage, popularity) {
}