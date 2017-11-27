package prueba.movil.prueba.data.model

import android.arch.persistence.room.Entity

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Entity
class Movie(id: Int, imgPoster: String, overview: String, date: String, genrerIds: Array<Int>, title: String,
            imgBackdrop: String, voteAverage: Double, popularity: Double, var adult: Boolean)
    : Item(id, imgPoster, overview, date, genrerIds, title, imgBackdrop, voteAverage, popularity) {
}