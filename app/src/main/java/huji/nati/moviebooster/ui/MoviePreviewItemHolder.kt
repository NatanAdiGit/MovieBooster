package huji.nati.moviebooster.ui

import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import huji.nati.moviebooster.R

class MoviePreviewItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val movieImage : ImageView = view.findViewById(R.id.imageView)
    val movieTitle : TextView = view.findViewById(R.id.movieTitleTextView)
    val releaseDate : TextView = view.findViewById(R.id.releaseDateTextView)
    private val ratingBar : RatingBar = view.findViewById(R.id.ratingBar)

    /**
     * sets the rating.
     */
    fun setRatingBar(rating:Float) {
        ratingBar.rating = rating / 2
    }

    /**
     * sets the image view.
     */
    fun setImageView(path: String) {
        Glide.with(view).load(path).into(movieImage)
    }
}