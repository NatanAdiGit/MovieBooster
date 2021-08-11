package huji.nati.moviebooster

import retrofit2.Call
import retrofit2.http.GET

interface MoviesServer {
    @GET("")
    public fun getTopRatedMovies() : Call<MovieData>
}