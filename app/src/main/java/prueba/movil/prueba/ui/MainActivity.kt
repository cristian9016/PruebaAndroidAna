package prueba.movil.prueba.ui

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import prueba.movil.prueba.R
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.ui.adapter.ItemAdapter
import prueba.movil.prueba.ui.fragments.MovieFragment
import prueba.movil.prueba.ui.fragments.SerieFragment
import prueba.movil.prueba.util.LifeDisposable
import prueba.movil.prueba.util.applySchedulers
import prueba.movil.prueba.util.buildViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, DrawerLayout.DrawerListener {

    var dis: LifeDisposable = LifeDisposable(this)
    lateinit var toggle: ActionBarDrawerToggle
    val mainViewModel: MainViewModel by lazy { buildViewModel(factory, MainViewModel::class) }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawer.addDrawerListener(this)
        //supportActionBar?.setTitle(R.string.categoria)

        nav.setNavigationItemSelectedListener { setContent(it) }
    }

    override fun onResume() {
        super.onResume()

        dis add adapter.clickItem
                .applySchedulers()
                .subscribeBy { goToDetail(it) }
    }

    fun goToDetail(item: Item){
        startActivity<DetailActivity>("params" to item)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>
            = injector


    fun AppCompatActivity.putFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(container, fragment)
                .commit()
    }

    fun setContent(item: MenuItem?): Boolean {
        drawer.closeDrawers()
        when(item?.itemId){
            R.id.movies -> putFragment(R.id.container, MovieFragment.instance())
            R.id.series -> putFragment(R.id.container, SerieFragment.instance())
        }
        return true
    }

    //region toggle
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDrawerStateChanged(newState: Int) {
        toggle.onDrawerStateChanged(newState)
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        toggle.onDrawerSlide(drawerView, slideOffset)
    }

    override fun onDrawerClosed(drawerView: View) {
        toggle.onDrawerClosed(drawerView)
    }

    override fun onDrawerOpened(drawerView: View) {
        toggle.onDrawerOpened(drawerView)
    }
    //endregion

}
