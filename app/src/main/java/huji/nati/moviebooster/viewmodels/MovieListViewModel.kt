package huji.nati.moviebooster

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import huji.nati.moviebooster.workers.CallMostPopularMoviesWork
import java.lang.reflect.Type
import java.util.*

class MovieListViewModel : ViewModel() {

    private val mainApp by lazy {MovieBoosterApp.instance}
    val adapter: MoviesAdapter by lazy { MoviesAdapter()}

    // The list of movies to display on the main screen.
    private val _displayedMoviesLiveData: MutableLiveData<List<MovieData>> = MutableLiveData()
    val displayedMoviesLiveData: LiveData<List<MovieData>> get() = _displayedMoviesLiveData


    fun getPopularMoviesList()
    {
        val workRequest = OneTimeWorkRequest.Builder(CallMostPopularMoviesWork::class.java)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()
        MovieBoosterApp.workManager.enqueue(workRequest)

        val liveData = MovieBoosterApp.workManager.getWorkInfoByIdLiveData(workRequest.id)
        // todo - un observe
        liveData.observeForever{ workInfo ->
            if (workInfo == null)
                return@observeForever
            if(workInfo.state == WorkInfo.State.SUCCEEDED) {
                _displayedMoviesLiveData.value = mainApp.getDisplayedMovieListFromSP()
                Log.e("size", _displayedMoviesLiveData.value!!.size.toString())
                adapter.setItems(_displayedMoviesLiveData.value!!)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
