package prueba.movil.prueba.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import prueba.movil.prueba.ui.MainViewModel
import prueba.movil.prueba.util.AppViewModelFactory
import kotlin.reflect.KClass


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal  annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule{

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindFarmViewModel(viewModel: MainViewModel): ViewModel



}