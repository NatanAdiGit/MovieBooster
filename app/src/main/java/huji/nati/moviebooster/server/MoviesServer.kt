package huji.nati.moviebooster.server

import huji.nati.moviebooster.JSONResponse
import huji.nati.moviebooster.MovieData
import org.jetbrains.annotations.PropertyKey
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServer {
    @GET("/3/movie/top_rated?api_key=8b5f74236568edb9ea59d41fce125d91")
    fun getTopRatedMovies() : Call<JSONResponse>

}