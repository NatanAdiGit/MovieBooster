package huji.nati.moviebooster.viewmodels

import androidx.lifecycle.ViewModel
import huji.nati.moviebooster.model.MovieBoosterApp
import huji.nati.moviebooster.model.MovieData

class SingleMovieViewModel : ViewModel() {

    private val mainApp by lazy { MovieBoosterApp.instance}


    fun getMovieToDisplay() : MovieData {
        return mainApp.getSingleMovieToDisplayFromSP()
    }
}