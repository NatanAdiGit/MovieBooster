package huji.nati.moviebooster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.ui.MoviePreviewItemHolder

class MoviesAdapter : RecyclerView.Adapter<MoviePreviewItemHolder>() {

    private val mainApp by lazy { MovieBoosterApp.instance}

    private val generalImagesDirectoryPath by lazy {"https://image.tmdb.org/t/p/w500"}

    var onImageClickCallback : ((MovieData) -> Unit)?= null

    override fun getItemCount(): Int {
        if (mainApp.displayedMoviesLiveData.value != null)
            return 0
        return mainApp.displayedMoviesLiveData.value!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePreviewItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.movie_preview_item, parent, false)
        return MoviePreviewItemHolder(view)

    }

    override fun onBindViewHolder(holder: MoviePreviewItemHolder, position: Int) {
        if (mainApp.displayedMoviesLiveData.value != null) {
            val currentMovie = mainApp.displayedMoviesLiveData.value!![position]

            holder.movieTitle.text = currentMovie.title
            holder.releaseDate.text = currentMovie.release_date

            // set the movie image
            holder.setImageView(generalImagesDirectoryPath + currentMovie.poster_path)

            // set the rating
            holder.setRatingBar(currentMovie.vote_average.toFloat())

            holder.movieImage.setOnClickListener {
                val callback = onImageClickCallback ?: return@setOnClickListener
                callback(currentMovie)
            }

        }

    }
}