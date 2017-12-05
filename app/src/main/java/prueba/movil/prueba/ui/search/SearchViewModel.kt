package prueba.movil.prueba.ui.search

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.data.model.Serie
import prueba.movil.prueba.data.net.SearchClient
import prueba.movil.prueba.util.applySchedulers
import javax.inject.Inject

/**
 * Created by Ana Marin on 4/12/2017.
 */
class SearchViewModel @Inject constructor(private val client: SearchClient,
                                          private val apiKey: String) : ViewModel() {

    fun searchSerie(query: String, page: Int, type: Int): Observable<List<Serie>> =
            client.searchSerie(apiKey, page, "es-ES", query)
                    .map { it.results }
                    .applySchedulers()

    fun searchMovie(query: String, page: Int, type: Int): Observable<List<Movie>> =
            client.searchMovie(apiKey, page, "es-ES", query)
                    .map { it.results }
                    .applySchedulers()

}