package prueba.movil.prueba.ui.main.movie


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.toast

import prueba.movil.prueba.R
import prueba.movil.prueba.di.Injectable
import prueba.movil.prueba.ui.adapter.ItemAdapter
import prueba.movil.prueba.ui.main.MainNavigation
import prueba.movil.prueba.util.*
import javax.inject.Inject
import com.jakewharton.rxbinding2.widget.RxTextView
import prueba.movil.prueba.data.model.Movie


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), Injectable {

    @Inject
    lateinit var adapter: ItemAdapter
    @Inject
    lateinit var nav: MainNavigation

    val dis: LifeDisposable = LifeDisposable(this)
    val category: Int by lazy { arguments!!.getInt(EXTRA_CATEGORY) }

    @Inject
    lateinit var factory: AppViewModelFactory
    val viewModel:MovieViewModel by lazy { buildViewModel(factory, MovieViewModel::class) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onResume() {
        super.onResume()
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)

        dis add viewModel.getFirstPage(category).subscribeByShot(
                onNext = {
                    adapter.items = it
                },
                onHttpError = { toast(it) },
                onError = { toast(it.message!!) })

        dis add adapter.clickItem
                .applySchedulers()
                .subscribeBy (onNext = {nav.navigateToDetail(it)} )
    }

    companion object {
        val EXTRA_CATEGORY = "category"
        fun instance(itemCategory:Int): MovieFragment{
            val fragment = MovieFragment()
            val args = Bundle()
            args.putInt(EXTRA_CATEGORY,itemCategory)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
