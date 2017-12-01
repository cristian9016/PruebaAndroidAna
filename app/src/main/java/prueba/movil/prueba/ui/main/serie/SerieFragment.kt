package prueba.movil.prueba.ui.main.serie


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_serie.*
import org.jetbrains.anko.support.v4.toast
import prueba.movil.prueba.R
import prueba.movil.prueba.di.Injectable
import prueba.movil.prueba.ui.adapter.ItemAdapter
import prueba.movil.prueba.ui.main.MainNavigation
import prueba.movil.prueba.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class SerieFragment : Fragment(), Injectable {

    val dis: LifeDisposable = LifeDisposable(this)
    val category: Int by lazy { arguments!!.getInt(EXTRA_CATEGORY) }

    @Inject
    lateinit var adapter: ItemAdapter

    @Inject
    lateinit var factory: AppViewModelFactory
    val viewModel: SerieViewModel by lazy { buildViewModel(factory, SerieViewModel::class) }

    @Inject
    lateinit var nav: MainNavigation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serie, container, false)
    }

    override fun onResume() {
        super.onResume()

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)

        dis add viewModel.getFirstPage(category).subscribeByShot(
                onNext = {
                    adapter.items = it },
                onHttpError = { toast(it) },
                onError = { toast(it.message!!) })

        dis add adapter.clickItem
                .applySchedulers()
                .subscribeBy (onNext = {nav.navigateToDetail(it)} )

    }

    companion object {
        val EXTRA_CATEGORY = "category"
        fun instance(itemCategory: Int): SerieFragment {
            val fragment = SerieFragment()
            val args = Bundle()
            args.putInt(EXTRA_CATEGORY, itemCategory)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
