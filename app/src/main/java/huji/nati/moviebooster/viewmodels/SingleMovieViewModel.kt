package huji.nati.moviebooster.viewmodels

import androidx.lifecycle.ViewModel
import huji.nati.moviebooster.MovieBoosterApp
import huji.nati.moviebooster.MovieData

class SingleMovieViewModel : ViewModel() {

    private val mainApp by lazy { MovieBoosterApp.instance}


    fun getMovieToDisplay() : MovieData {
        return mainApp.getSingleMovieToDisplayFromSP()
    }
}