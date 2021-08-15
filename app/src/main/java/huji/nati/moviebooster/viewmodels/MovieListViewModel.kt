package huji.nati.moviebooster

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import huji.nati.moviebooster.model.MovieBoosterApp
import huji.nati.moviebooster.model.MovieData

class MovieListViewModel : ViewModel() {

    private val mainApp by lazy { MovieBoosterApp.instance}

    fun getMoviesListLiveData() : LiveData<List<MovieData>> {
        return mainApp.displayedMovieListLiveData
    }

    fun getPopularMovieList() {
        mainApp.requestPopularMoviesList()
    }

    fun setSingleMovieToDisplay(movieData: MovieData) {
        mainApp.setSingleMovieToDisplayToSP(movieData)
    }
}
