package prueba.movil.prueba.data.net

import io.reactivex.Observable
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.data.model.Serie
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ana Marin on 26/11/2017.
 */
interface SerieClient{

    @GET("/3/tv/popular")
    fun getPopular(@Query("api_key") apiKey: String, @Query("page") page:Int, @Query ("language") language:String): Observable<Response<Serie>>

    @GET("/3/tv/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String, @Query("page") page:Int, @Query ("language") language:String): Observable<Response<Serie>>


}