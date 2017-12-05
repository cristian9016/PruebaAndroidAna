package prueba.movil.prueba.ui.search

import android.app.SearchManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_search.*
import prueba.movil.prueba.R
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.di.Injectable
import prueba.movil.prueba.ui.adapter.ItemAdapter
import prueba.movil.prueba.util.AppViewModelFactory
import prueba.movil.prueba.util.LifeDisposable
import prueba.movil.prueba.util.buildViewModel
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, Injectable {

    @Inject
    lateinit var factory: AppViewModelFactory
    @Inject
    lateinit var adapter: ItemAdapter<Item>
    val viewModel: SearchViewModel by lazy { buildViewModel<SearchViewModel>(factory) }
    val type by lazy { intent.extras.getInt(EXTRA_SEARCH) }

    val dis: LifeDisposable = LifeDisposable(this)
    var query:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        results.layoutManager = LinearLayoutManager(this)
        results.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        /*dis add adapter.scrollEnd
                .flatMap { Observable.create<Int> {
                    if(query!=null) it.onNext(1)
                    it.onComplete()
                }}
                .scan(1, {a, v -> a + v})
                .flatMap { viewModel.search(query!!, it, type) }
                .subscribe {
                    adapter.addItems(it)
                }*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(true)
        searchView.isIconified = false
        searchView.queryHint = if (type == SEARCH_MOVIE) getString(R.string.search_movies)
        else getString(R.string.search_series)

        searchView.setOnCloseListener {
            searchView.setQuery("", false)
            query = null
            true
        }
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        this.query = query
        search(type, 1)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true

    fun search(type:Int, page:Int) {
        when(type){
            SEARCH_MOVIE -> dis add viewModel.searchMovie(query!!, 1, type)
                    .subscribe { adapter.items = it.toMutableList() }
            SEARCH_SERIE -> dis add viewModel.searchSerie(query!!, 1, type)
                .subscribe { adapter.items = it.toMutableList() }

        }


    }

    companion object {
        val EXTRA_SEARCH = "extra_search"
        @JvmStatic
        val SEARCH_MOVIE = 0
        @JvmStatic
        val SEARCH_SERIE = 1
    }
}
