package prueba.movil.prueba.ui.main.movie


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_serie.*

import prueba.movil.prueba.R
import prueba.movil.prueba.di.Injectable
import prueba.movil.prueba.ui.adapter.ItemAdapter
import prueba.movil.prueba.ui.main.MainNavigation
import prueba.movil.prueba.util.LifeDisposable
import prueba.movil.prueba.util.applySchedulers
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), Injectable {

    @Inject
    lateinit var adapter: ItemAdapter
    @Inject
    lateinit var nav: MainNavigation

    val dis: LifeDisposable = LifeDisposable(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onResume() {
        super.onResume()
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)

        dis add adapter.clickItem
                .applySchedulers()
                .subscribeBy (onNext = {nav.navigateToDetail(it)} )
    }

    companion object {
        fun instance(): MovieFragment = MovieFragment()
    }

}// Required empty public constructor
