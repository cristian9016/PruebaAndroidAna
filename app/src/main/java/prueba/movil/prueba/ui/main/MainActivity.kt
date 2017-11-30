package prueba.movil.prueba.ui.main

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
import kotlinx.android.synthetic.main.activity_main.*
import prueba.movil.prueba.R
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.ui.main.movie.MovieFragment
import prueba.movil.prueba.ui.main.serie.SerieFragment
import prueba.movil.prueba.util.putFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, DrawerLayout.DrawerListener {

    val toggle: ActionBarDrawerToggle by lazy {
        ActionBarDrawerToggle(this, drawer, R.string.menu_open, R.string.menu_close)
    }

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawer.addDrawerListener(this)
        nav.setNavigationItemSelectedListener { setContent(it.itemId) }
        setContent(R.id.nav_upcoming_movies)

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>
            = injector


    fun setContent(idNav: Int): Boolean {
        drawer.closeDrawers()
        when (idNav) {
            R.id.nav_popular_movies -> putFragment(R.id.container, MovieFragment.instance(Item.CATEGORY_POPULAR), supportActionBar!!.setTitle(R.string.popular))
            R.id.nav_top_rated_movies -> putFragment(R.id.container, MovieFragment.instance(Item.CATEGORY_TOP_RATED), supportActionBar!!.setTitle(R.string.top_rated))
            R.id.nav_upcoming_movies -> putFragment(R.id.container, MovieFragment.instance(Item.CATEGORY_UPCOMING), supportActionBar!!.setTitle(R.string.upcoming))
            R.id.nav_popular_series -> putFragment(R.id.container, SerieFragment.instance(Item.CATEGORY_POPULAR), supportActionBar!!.setTitle(R.string.popular))
            R.id.nav_top_rated_series -> putFragment(R.id.container, SerieFragment.instance(Item.CATEGORY_TOP_RATED), supportActionBar!!.setTitle(R.string.top_rated))
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
