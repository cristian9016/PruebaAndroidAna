package prueba.movil.prueba.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import prueba.movil.prueba.App
import prueba.movil.prueba.di.ActivityBuilder
import prueba.movil.prueba.di.module.AppModule
import prueba.movil.prueba.di.module.NetModule
import prueba.movil.prueba.di.module.ViewModelModule
import javax.inject.Singleton

/**
 * Created by Ana Marin on 26/11/2017.
 */

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        NetModule::class,
        ViewModelModule::class
))
interface AppComponent{
    fun inject(app: App)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun applicacion(application: Application):Builder
        fun build():AppComponent
    }

}
