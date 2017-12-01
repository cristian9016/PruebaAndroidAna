package prueba.movil.prueba.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ana Marin on 26/11/2017.
 */

abstract class Item {

    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
    var popularity: Double = 0.0
    lateinit var overview: String
    var category: Int = 0

    abstract fun getType():Int

    companion object {
        @JvmStatic
        val TYPE_SERIE: Int = 0
        @JvmStatic
        val TYPE_MOVIE: Int = 1

        val CATEGORY_TOP_RATED = 0
        val CATEGORY_POPULAR = 1
        val CATEGORY_UPCOMING = 2
    }
}
