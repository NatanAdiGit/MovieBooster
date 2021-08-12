package huji.nati.moviebooster.server

import huji.nati.moviebooster.MovieData
import retrofit2.Call
import retrofit2.http.GET

interface MoviesServer {
    @GET("")
    public fun getTopRatedMovies() : Call<List<MovieData>>
}