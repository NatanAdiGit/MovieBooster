package huji.nati.moviebooster.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.model.MovieBoosterApp
import huji.nati.moviebooster.model.MovieData
import huji.nati.moviebooster.R
import huji.nati.moviebooster.ui.holders.MoviePreviewItemHolder

class MoviesAdapter : RecyclerView.Adapter<MoviePreviewItemHolder>() {

    private val movieList : MutableList<MovieData> = mutableListOf()

    var onMovieClickListener : ((MovieData) -> Unit)?= null

    fun setItems(items : List<MovieData>) {
        movieList.clear()
        movieList.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePreviewItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.movie_preview_item, parent, false)
        return MoviePreviewItemHolder(view)
    }

    override fun onBindViewHolder(holder: MoviePreviewItemHolder, position: Int) {
        val currentMovie = movieList[position]

        holder.movieTitle.text = currentMovie.title

        // set the release date.
        holder.setDateView(currentMovie.release_date)

        // set the movie image
        holder.setImageView(MovieBoosterApp.imagesDirectoryGeneralURL + currentMovie.poster_path)

        // set the rating
        holder.setRatingBar(currentMovie.vote_average)

        // set on current view click listener
        holder.itemView.setOnClickListener {
            val callback = onMovieClickListener ?: return@setOnClickListener
            callback(movieList[position])
        }
    }
}