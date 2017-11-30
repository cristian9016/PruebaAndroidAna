package prueba.movil.prueba.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import prueba.movil.prueba.R
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
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(context.getString(R.string.base_url))
            .build()

    @Provides
    @Singleton
    fun provideMovieClient(retrofit: Retrofit): MovieClient =
            retrofit.create(MovieClient::class.java)

    @Provides
    @Singleton
    fun provideSerieClient(retrofit: Retrofit): SerieClient =
            retrofit.create(SerieClient::class.java)

    @Singleton
    @Provides
    fun provideApiKey(context: Context): String = context.getString(R.string.apiKey)

}