package huji.nati.moviebooster.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import huji.nati.moviebooster.*
import huji.nati.moviebooster.workers.CallAutoCompleteWork
import huji.nati.moviebooster.workers.CallMostPopularMoviesWork

class SearchViewModel : ViewModel() {

    private val mainApp by lazy { MovieBoosterApp.instance}
    val adapter: AutocompleteAdapter by lazy {AutocompleteAdapter()}

    // The list of movies to display on the main screen.
    private val _displayedMoviesLiveData: MutableLiveData<List<AutocompleteData>> = MutableLiveData()
    val displayedMoviesLiveData: LiveData<List<AutocompleteData>> get() = _displayedMoviesLiveData

    fun updatesAutoCompleteOptions(query : String) {

        val workRequest = OneTimeWorkRequest.Builder(CallAutoCompleteWork::class.java)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setInputData(
                Data.Builder()
                    .putString("query", query)
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

        .setInputData(
            Data.Builder()
                .putLong("number", number.number)
                .build()
        )
    }
}