package prueba.movil.prueba.ui.main.serie

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import prueba.movil.prueba.data.dao.SerieDao
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.data.model.Serie
import prueba.movil.prueba.net.SerieClient
import prueba.movil.prueba.util.applySchedulers
import javax.inject.Inject

/**
 * Created by Ana Marin on 28/11/2017.
 */
class SerieViewModel @Inject constructor(private val client: SerieClient, private val dao: SerieDao,
                                         private val apiKey: String) : ViewModel() {

    fun getFirstPage(category: Int): Observable<List<Serie>> =
            Observable.concat(dao.lastest(category).toObservable(), getSeries(category, 0))
            .applySchedulers()

    fun getSeriesByPage(category: Int, page: Int) = getSeries(category, page)
            .applySchedulers()

    private fun getSeries(category: Int, page: Int) = when (category) {
        Item.CATEGORY_POPULAR -> client.getPopular(apiKey, page)
        else -> client.getTopRated(apiKey, page)
    }
            .map {
                if (page == 0) dao.removeByCategory(category)
                dao.insert(it.results.map {
                    it.category = category
                    it
                })
                it.results
            }


}