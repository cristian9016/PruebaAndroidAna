package prueba.movil.prueba.ui.main.movie

import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable
import prueba.movil.prueba.data.dao.MovieDao
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.net.MovieClient
import prueba.movil.prueba.util.applySchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Ana Marin on 28/11/2017.
 */
class MovieViewModel @Inject constructor(private val client: MovieClient,
                                         private val dao: MovieDao, private val apiKey: String) : ViewModel() {

    fun getFirstPage(category: Int): Observable<List<Movie>> = dao.lastest(category).toObservable()
            .concatWith(Observable.timer(200, TimeUnit.MILLISECONDS)
                    .flatMap { getMovies(category, 1) })
            .applySchedulers()

    fun getMoviesByPage(category: Int, page: Int) = getMovies(category, page)
            .applySchedulers()

    private fun getMovies(category: Int, page: Int) = when (category) {
        Item.CATEGORY_POPULAR -> client.getPopular(apiKey, page)
        Item.CATEGORY_UPCOMING -> client.getUpcoming(apiKey, page)
        else -> client.getTopRated(apiKey, page)
    }
            .map {
                if (page == 1) dao.removeByCategory(category)
                dao.insert(it.results.map {
                    it.category = category
                    it
                })
                it.results
            }

}