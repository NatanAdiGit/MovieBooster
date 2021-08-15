package huji.nati.moviebooster.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import huji.nati.moviebooster.BuildConfig
import huji.nati.moviebooster.model.MovieBoosterApp
import huji.nati.moviebooster.server.MoviesServer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class CallMovieWorker(context: Context, workerParams : WorkerParameters)
    : Worker(context, workerParams) {

    protected val okHttpClient: OkHttpClient by lazy {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        return@lazy okHttpClientBuilder.build()
    }

    protected val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(MovieBoosterApp.dataBaseGeneralURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    protected val serverInterface: MoviesServer by lazy { retrofit.create(MoviesServer::class.java) }
}