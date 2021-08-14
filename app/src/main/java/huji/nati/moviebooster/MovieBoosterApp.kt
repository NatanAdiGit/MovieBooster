package huji.nati.moviebooster

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import huji.nati.moviebooster.workers.CallMostPopularMoviesWork
import java.lang.reflect.Type

class MovieBoosterApp : Application(){

    private lateinit var sp : SharedPreferences
    private lateinit var gson : Gson

    companion object {
        lateinit var instance: MovieBoosterApp
            private set

        lateinit var workManager : WorkManager
            private set

        lateinit var dataBaseGeneralURL : String
        private set

        lateinit var imagesDirectoryGeneralURL : String
        private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        workManager = WorkManager.getInstance(this)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        gson = Gson()
        dataBaseGeneralURL = "https://api.themoviedb.org"
        imagesDirectoryGeneralURL = "https://image.tmdb.org/t/p/w500"

    }


    fun setDisplayedMovieListToSP(newList : List<MovieData>) {
        val editor : SharedPreferences.Editor = sp.edit()
        editor.putString("displayed_movie_list", gson.toJson(newList))
        editor.apply()
    }

    fun getDisplayedMovieListFromSP() : List<MovieData> {
        val jsonInProcess: String = sp.getString("displayed_movie_list", "")!!
        val type: Type = object : TypeToken<MutableList<MovieData>>() {}.type
        if (jsonInProcess != "") {
            return gson.fromJson(jsonInProcess, type)
        }
        return listOf()
    }

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

    fun setAutoCompleteResultsToSP(newList : List<AutocompleteData>) {
        val editor : SharedPreferences.Editor = sp.edit()
        editor.putString("autocomplete_results", gson.toJson(newList))
        editor.apply()
    }

    fun getAutoCompleteResultsFromSP() :  List<AutocompleteData> {
        val jsonInProcess: String = sp.getString("autocomplete_results", "")!!
        val type: Type = object : TypeToken<MutableList<AutocompleteData>>() {}.type
        if (jsonInProcess != "") {
            return gson.fromJson(jsonInProcess, type)
        }
        return listOf()
    }


//
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