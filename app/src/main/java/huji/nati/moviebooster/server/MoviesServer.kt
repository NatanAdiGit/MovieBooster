package huji.nati.moviebooster.server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServer {
    @GET("/3/movie/popular?api_key=8b5f74236568edb9ea59d41fce125d91")
    fun getPopularMovieList() : Call<JSONMovieDataResponse>

    @GET("/3/search/movie?api_key=8b5f74236568edb9ea59d41fce125d91&page=1")
    fun getSearchMoveList(@Query("query") query : String) : Call<JSONMovieDataResponse>

    @GET("/3/search/keyword?api_key=8b5f74236568edb9ea59d41fce125d91&page=1")
    fun getAutoCompleteList(@Query("query") query : String) : Call<JSONAutocompleteResponse>
}