package prueba.movil.prueba.di.module

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import prueba.movil.prueba.net.MovieClient
import prueba.movil.prueba.net.SerieClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Ana Marin on 26/11/2017.
 */
@Module
class NetModule{
    @Provides
    @Singleton
    fun provideRetrofit(context: Context): Retrofit = Retrofit.
            Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create()
            ))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl("https://api.themoviedb.org/3/list/1/}")
            .build()

    @Provides
    @Singleton
    fun provideMovieClient(retrofit: Retrofit): MovieClient =
            retrofit.create(MovieClient::class.java)

    @Provides
    @Singleton
    fun provideSerieClient(retrofit: Retrofit): SerieClient =
            retrofit.create(SerieClient::class.java)

}