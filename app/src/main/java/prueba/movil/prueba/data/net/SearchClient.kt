package prueba.movil.prueba.data.net

import io.reactivex.Observable
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.data.model.Serie
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ana Marin on 4/12/2017.
 */
interface SearchClient{

    @GET("/3/search/movie")
    fun searchMovie(@Query ("api_key") apiKey: String, @Query("page") page:Int, @Query("language") language:String, @Query("query") query:String): Observable<Response<Movie>>

    @GET("/3/search/tv")
    fun searchSerie(@Query("api_key") apiKey: String, @Query("page") page:Int, @Query("language") language:String, @Query("query") query:String): Observable<Response<Serie>>
}