package huji.nati.moviebooster

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CallMoviesServerWork(context: Context, workerParams : WorkerParameters) : Worker(context, workerParams){

    override fun doWork(): Result {

        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(interceptor)
        }

        val okHttpClient = okHttpClientBuilder.build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serverInterface = retrofit.create(MoviesServer::class.java)

        try {
            val response = serverInterface.getTopRatedMovies().execute() // blocked until results
            val responseBody = response.body() ?: return Result.failure()

        }
    }
}
