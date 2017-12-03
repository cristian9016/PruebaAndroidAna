package prueba.movil.prueba.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import prueba.movil.prueba.di.module.MainModule
import prueba.movil.prueba.ui.detail.DetailActivity
import prueba.movil.prueba.ui.main.MainActivity

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Module
abstract class ActivityBuilder{
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity
}