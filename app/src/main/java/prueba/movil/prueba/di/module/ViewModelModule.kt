package prueba.movil.prueba.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import prueba.movil.prueba.ui.main.movie.MovieViewModel
import prueba.movil.prueba.ui.main.serie.SerieViewModel
import prueba.movil.prueba.ui.search.SearchViewModel
import prueba.movil.prueba.util.AppViewModelFactory
import kotlin.reflect.KClass


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal  annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SerieViewModel::class)
    abstract fun bindSerieViewModel(viewModel: SerieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory



}