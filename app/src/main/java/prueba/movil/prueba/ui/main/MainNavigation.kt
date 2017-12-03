package prueba.movil.prueba.ui.main

import org.jetbrains.anko.startActivity
import prueba.movil.prueba.data.Global
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.data.model.Serie
import prueba.movil.prueba.di.ActivityScope
import prueba.movil.prueba.ui.detail.DetailActivity
import javax.inject.Inject

/**
 * Created by Ana Marin on 29/11/2017.
 */
@ActivityScope
class MainNavigation @Inject constructor(val activity: MainActivity,
                                         private val global: Global){

    fun navigateToDetail(item: Item){
        global.selected = item
        activity.startActivity<DetailActivity>()
    }

}