package huji.nati.moviebooster.workers

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import huji.nati.moviebooster.model.MovieBoosterApp


class CallMostPopularMoviesWork(context: Context, workerParams : WorkerParameters)
    : CallMovieWorker(context, workerParams){

    private val mainApp by lazy { MovieBoosterApp.instance}

    override fun doWork(): Result {
        try {
            val response = serverInterface.getPopularMovieList().execute() // blocked until results
            val responseBody = response.body() ?: return Result.failure()

            // set the displayed movies to be the most popular movies according to server.
            mainApp.postToDisplayedMovieListLiveData(responseBody.results)
            return Result.success()
        }
        catch (e : Exception) {
            Log.e("exception", e.toString())
            return Result.failure()
        }
    }
}