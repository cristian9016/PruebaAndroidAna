package prueba.movil.prueba.net

import io.reactivex.Observable
import prueba.movil.prueba.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by Ana Marin on 26/11/2017.
 */
interface MovieClient{

    @GET("/")
    fun getAll(@Query ("api_key") apiKey: String, @Query("page") page:Int): Observable<Response<Movie>>

    @GET("/3/movie/popular")
    fun getPopular(@Query ("api_key") apiKey: String, @Query("page") page:Int): Observable<Response<Movie>>

    @GET("/3/movie/top_rated")
    fun getTopRated(@Query ("api_key") apiKey: String, @Query("page") page:Int): Observable<Response<Movie>>

    @GET("/3/movie/upcoming")
    fun getUpcoming(@Query ("api_key") apiKey: String, @Query("page") page:Int): Observable<Response<Movie>>
}