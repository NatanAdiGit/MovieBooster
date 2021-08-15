package huji.nati.moviebooster.workers

import android.content.Context
import androidx.work.WorkerParameters
import huji.nati.moviebooster.model.MovieBoosterApp

class CallAutoCompleteWork (context: Context, workerParams : WorkerParameters)
    : CallMovieWorker(context, workerParams) {

    private val mainApp by lazy { MovieBoosterApp.instance }

    override fun doWork(): Result {
        try {
            val query: String? = inputData.getString("query")
            if (query != null) {
                val response =
                    serverInterface.getAutoCompleteList(query).execute() // blocked until results
                val responseBody = response.body() ?: return Result.failure()

                // set the autocomplete list movies according to server.
                mainApp.postToAutocompleteLiveData(responseBody.results)
                return Result.success()
            }
            else
                return Result.failure()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}