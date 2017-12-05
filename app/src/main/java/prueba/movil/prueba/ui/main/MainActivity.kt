package prueba.movil.prueba.ui.main

import android.app.SearchManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
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
import org.jetbrains.anko.startActivity
import prueba.movil.prueba.ui.search.SearchActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, DrawerLayout.DrawerListener {

    val toggle: ActionBarDrawerToggle by lazy {
        ActionBarDrawerToggle(this, drawer, R.string.menu_open, R.string.menu_close)
    }
    var searchType:Int = SearchActivity.SEARCH_MOVIE

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawer.addDrawerListener(this)
        nav.setNavigationItemSelectedListener { setContent(it.itemId) }
        setContent(R.id.nav_popular_movies)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>
            = injector


    fun setContent(idNav: Int): Boolean {
        drawer.closeDrawers()
        when (idNav) {
            R.id.nav_popular_movies -> putContent(R.string.movie_popular, MovieFragment.instance(Item.CATEGORY_POPULAR), SearchActivity.SEARCH_MOVIE)
            R.id.nav_top_rated_movies -> putContent(R.string.movie_top_rated, MovieFragment.instance(Item.CATEGORY_TOP_RATED), SearchActivity.SEARCH_MOVIE)
            R.id.nav_upcoming_movies -> putContent(R.string.movie_upcoming, MovieFragment.instance(Item.CATEGORY_UPCOMING), SearchActivity.SEARCH_MOVIE)
            R.id.nav_popular_series -> putContent(R.string.serie_popular, SerieFragment.instance(Item.CATEGORY_POPULAR), SearchActivity.SEARCH_SERIE)
            R.id.nav_top_rated_series -> putContent(R.string.serie_top_rated, SerieFragment.instance(Item.CATEGORY_TOP_RATED), SearchActivity.SEARCH_SERIE)
        }
        return true
    }

    fun putContent(title:Int, fragment:Fragment, searchType:Int){
        putFragment(R.id.container, fragment)
        supportActionBar!!.setTitle(title)
        this.searchType = searchType
    }

    //region toggle
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }else startActivity<SearchActivity>(SearchActivity.EXTRA_SEARCH to searchType)
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
