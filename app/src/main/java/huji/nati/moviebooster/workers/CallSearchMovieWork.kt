package huji.nati.moviebooster.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import huji.nati.moviebooster.model.MovieBoosterApp

class CallSearchMovieWork (context: Context, workerParams : WorkerParameters)
    : CallMovieWorker(context, workerParams) {

    private val mainApp by lazy { MovieBoosterApp.instance }

    override fun doWork(): ListenableWorker.Result {
        try {
            val query: String? = inputData.getString("query")
            if (query != null) {
                val response =
                    serverInterface.getSearchMoveList(query).execute() // blocked until results
                val responseBody = response.body() ?: return ListenableWorker.Result.failure()

                // set the displayed movies to be the most popular movies according to server.
                mainApp.postToDisplayedMovieListLiveData(responseBody.results)
                return Result.success()
            }
            else
                return Result.failure()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}