package prueba.movil.prueba.net

import io.reactivex.Observable
import prueba.movil.prueba.data.model.Serie
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ana Marin on 26/11/2017.
 */
interface SerieClient{

    @GET("tv/popular")
    fun getPopular(@Query("api_key") apiKey: String, @Query("page") page:Int): Observable<Response<List<Serie>>>

    @GET("tv/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String, @Query("page") page:Int): Observable<Response<List<Serie>>>

}