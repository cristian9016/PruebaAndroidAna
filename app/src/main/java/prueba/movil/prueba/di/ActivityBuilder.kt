package prueba.movil.prueba.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import prueba.movil.prueba.ui.MainActivity

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Module
abstract class ActivityBuilder{
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}