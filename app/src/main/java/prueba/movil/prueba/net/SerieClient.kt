package prueba.movil.prueba.net

import io.reactivex.Observable
import prueba.movil.prueba.data.model.Serie
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ana Marin on 26/11/2017.
 */
interface SerieClient{

    @GET("/")
    fun getAllMovies(@Query("api_key") apiKey: String): Observable<Response<List<Serie>>>

    @GET("tv/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<Response<List<Serie>>>

    @GET("tv/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String): Observable<Response<List<Serie>>>

}