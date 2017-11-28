package prueba.movil.prueba.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import prueba.movil.prueba.di.FragmentScope
import prueba.movil.prueba.ui.main.movie.MovieFragment
import prueba.movil.prueba.ui.main.serie.SerieFragment

@Module
abstract class MainModule{

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bidnMovieFragment(): MovieFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindSerieFragment(): SerieFragment

}