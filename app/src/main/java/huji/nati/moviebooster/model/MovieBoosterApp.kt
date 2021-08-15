package huji.nati.moviebooster.model

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import androidx.work.*
import huji.nati.moviebooster.workers.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
//import huji.nati.moviebooster.MovieClient
import java.lang.reflect.Type

class MovieBoosterApp : Application(){

    private lateinit var sp : SharedPreferences
    private lateinit var gson : Gson

    companion object {
        lateinit var instance: MovieBoosterApp
            private set

        lateinit var workManager : WorkManager
            private set

//        lateinit var client : MovieClient

        lateinit var dataBaseGeneralURL : String
        private set

        lateinit var imagesDirectoryGeneralURL : String
        private set
    }

    // The list of movies to display on the main screen.
     val _displayedMovieListLiveData: MutableLiveData<List<MovieData>> by lazy {
        MutableLiveData()}

    val displayedMovieListLiveData: LiveData<List<MovieData>> = _displayedMovieListLiveData

    // The list of autocompletes to display on the search screen.
    private val _autocompleteLiveData: MutableLiveData<List<AutocompleteData>> by lazy {
        MutableLiveData()}

    val autocompleteLiveData: LiveData<List<AutocompleteData>> = _autocompleteLiveData

    override fun onCreate() {
        super.onCreate()

        instance = this
        workManager = WorkManager.getInstance(this)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        gson = Gson()
        dataBaseGeneralURL = "https://api.themoviedb.org"
        imagesDirectoryGeneralURL = "https://image.tmdb.org/t/p/w500"

    }

    fun requestPopularMoviesList()
    {
        try {
            val workRequest = OneTimeWorkRequest.Builder(CallMostPopularMoviesWork::class.java)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
            workManager.enqueue(workRequest)
        } catch (e: Exception) {
            _displayedMovieListLiveData.value = listOf()
        }
    }

    fun requestSearchMovieListByQuery(query : String)  {
        try {
            val workRequest = OneTimeWorkRequest.Builder(CallSearchMovieWork::class.java)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .setInputData(
                    Data.Builder()
                        .putString("query", query)
                        .build())
                .build()
            workManager.enqueue(workRequest)
        } catch (e: Exception) {
        }
    }

    fun requestAutocompleteByQuery(query : String)  {
        try {
            val workRequest = OneTimeWorkRequest.Builder(CallAutoCompleteWork::class.java)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .setInputData(
                    Data.Builder()
                        .putString("query", query)
                        .build())
                .build()
            workManager.enqueue(workRequest)
        } catch (e: Exception) {
            _autocompleteLiveData.value = listOf()
        }
    }

    fun postToDisplayedMovieListLiveData(lst : List<MovieData>) {
        _displayedMovieListLiveData.postValue(lst)
    }

    fun postToAutocompleteLiveData(lst : List<AutocompleteData>) {
        _autocompleteLiveData.postValue(lst)
    }

//    fun getMoviesListLiveData() : LiveData<List<MovieData>> {
//       return displayedMovieListLiveData
//    }
//
//    fun getAutocompleteLiveData(): LiveData<List<AutocompleteData>> {
//        return autocompleteLiveData
//    }


    fun setSingleMovieToDisplayToSP(movieData: MovieData) {
        val editor : SharedPreferences.Editor = sp.edit()
        editor.putString("displayed_single_movie", gson.toJson(movieData))
        editor.apply()
    }

    fun getSingleMovieToDisplayFromSP() : MovieData {
        val jsonInProcess: String = sp.getString("displayed_single_movie", "")!!
        val type: Type = object : TypeToken<MovieData>() {}.type
        if (jsonInProcess != "") {
            return gson.fromJson(jsonInProcess, type)
        }
        return MovieData()
    }

//    fun setMoviesToDisplay(newList : List<MovieData>) {
//        _displayedMovieListLiveData.value = newList
//    }
//
//    fun setAutoCompleteList(newList : List<AutocompleteData>) {
//        _autocompleteLiveData.value = newList
//    }


//    fun setDisplayedMovieListToSP(newList : List<MovieData>) {
//        val editor : SharedPreferences.Editor = sp.edit()
//        editor.putString("displayed_movie_list", gson.toJson(newList))
//        editor.apply()
//    }
//
//    fun getDisplayedMovieListFromSP() : List<MovieData> {
//        val jsonInProcess: String = sp.getString("displayed_movie_list", "")!!
//        val type: Type = object : TypeToken<MutableList<MovieData>>() {}.type
//        if (jsonInProcess != "") {
//            return gson.fromJson(jsonInProcess, type)
//        }
//        return listOf()
//    }



//    fun setAutoCompleteResultsToSP(newList : List<AutocompleteData>) {
//        val editor : SharedPreferences.Editor = sp.edit()
//        editor.putString("autocomplete_results", gson.toJson(newList))
//        editor.apply()
//    }
//
//    fun getAutoCompleteResultsFromSP() :  List<AutocompleteData> {
//        val jsonInProcess: String = sp.getString("autocomplete_results", "")!!
//        val type: Type = object : TypeToken<MutableList<AutocompleteData>>() {}.type
//        if (jsonInProcess != "") {
//            return gson.fromJson(jsonInProcess, type)
//        }
//        return listOf()
//    }
//
//
////
//    fun setPickedMovie(movieData : MovieData) {
//        _pickedMoviesLiveData.value = movieData
//    }
//
//    fun startPopularMoviesWork() {
//        val workRequest = OneTimeWorkRequest.Builder(CallMostPopularMoviesWork::class.java)
//            .setConstraints(Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build())
//            .build()
//
//        workManager.enqueue(workRequest)
//
//        val liveData = workManager.getWorkInfoByIdLiveData(workRequest.id)
//        liveData.observeForever(Observer {
//
//        })
//
//    }
//

}