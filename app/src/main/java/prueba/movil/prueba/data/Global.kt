package prueba.movil.prueba.data

import prueba.movil.prueba.data.model.Item
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ana Marin on 1/12/2017.
 */
@Singleton
class Global @Inject constructor(){
    lateinit var selected: Item
}