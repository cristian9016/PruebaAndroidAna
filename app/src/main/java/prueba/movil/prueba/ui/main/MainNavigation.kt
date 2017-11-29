package prueba.movil.prueba.ui.main

import org.jetbrains.anko.startActivity
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.data.model.Serie
import prueba.movil.prueba.ui.detail.DetailActivity
import javax.inject.Inject

/**
 * Created by Ana Marin on 29/11/2017.
 */
class MainNavigation @Inject constructor(val activity: MainActivity){

    fun navigateToDetail(movie: Movie){
        activity.startActivity<DetailActivity>("movie" to movie)
    }

    fun navigateToDetail(serie: Serie){
        activity.startActivity<DetailActivity>("serie" to serie)
    }
}