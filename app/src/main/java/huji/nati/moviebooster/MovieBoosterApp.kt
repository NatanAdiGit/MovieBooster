package huji.nati.moviebooster

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MovieBoosterApp : Application(){

    // The list of movies to display on the main screen.
    private val _displayedMoviesLiveData : MutableLiveData<List<MovieData>> = MutableLiveData()
    val displayedMoviesLiveData: LiveData<List<MovieData>> get() = _displayedMoviesLiveData

    // The movie that the user chose to look at.
    private val _pickedMoviesLiveData : MutableLiveData<MovieData> = MutableLiveData()
    val pickedMoviesLiveData: LiveData<MovieData> get() = _pickedMoviesLiveData

    companion object {
        lateinit var instance: MovieBoosterApp
            private set
    }


    fun setDisplayedMovieList(newList : List<MovieData>) {
        _displayedMoviesLiveData.value = newList
    }

    fun setPickedMovie(movieData : MovieData) {
        _pickedMoviesLiveData.value = movieData
    }

}