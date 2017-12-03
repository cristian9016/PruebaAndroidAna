package prueba.movil.prueba.ui.main.serie

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import prueba.movil.prueba.data.dao.SerieDao
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.data.model.Serie
import prueba.movil.prueba.data.net.SerieClient
import prueba.movil.prueba.util.applySchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Ana Marin on 28/11/2017.
 */
class SerieViewModel @Inject constructor(private val client: SerieClient, private val dao: SerieDao,
                                         private val apiKey: String) : ViewModel() {

    fun getFirstPage(category: Int): Observable<List<Serie>> = dao.lastest(category).toObservable()
            .concatWith(Observable.timer(200, TimeUnit.MILLISECONDS)
                    .flatMap{ getSeries(category, 1) })
            .applySchedulers()

    fun getSeriesByPage(category: Int, page: Int) = getSeries(category, page)
            .applySchedulers()

    private fun getSeries(category: Int, page: Int) = when (category) {
        Item.CATEGORY_POPULAR -> client.getPopular(apiKey, page, "es-ES")
        else -> client.getTopRated(apiKey, page, "es-ES")
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