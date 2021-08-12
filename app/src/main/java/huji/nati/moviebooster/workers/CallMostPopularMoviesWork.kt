package huji.nati.moviebooster.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import huji.nati.moviebooster.BuildConfig
import huji.nati.moviebooster.MovieBoosterApp
import huji.nati.moviebooster.server.MoviesServer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CallMostPopularMoviesWork(context: Context, workerParams : WorkerParameters)
    : Worker(context, workerParams){

    private val okHttpClient : OkHttpClient by lazy {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(interceptor)
        }

        return@lazy okHttpClientBuilder.build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val serverInterface by lazy {retrofit.create(MoviesServer::class.java)}

    private val mainApp by lazy {MovieBoosterApp.instance}

    override fun doWork(): Result {

        try {
            val response = serverInterface.getTopRatedMovies().execute() // blocked until results
            val responseBody = response.body() ?: return Result.failure()

            // set the displayed movies to be the most popular movies according to server.
            mainApp.setDisplayedMovieListToSP(responseBody)
            return Result.success()
        }
        catch (e : Exception) {
            return Result.failure()
        }
    }
}
