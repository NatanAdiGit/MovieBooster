package huji.nati.moviebooster.ui.holders

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import huji.nati.moviebooster.R

class MoviePreviewItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val movieImage : ImageView = view.findViewById(R.id.imageView)
    private val releaseDate : TextView = view.findViewById(R.id.releaseDateTextView)
    val movieTitle : TextView = view.findViewById(R.id.movieTitleTextView)
    private val ratingBar : RatingBar = view.findViewById(R.id.ratingBar)


    /**
     * sets the rating.
     */
    fun setRatingBar(rating:Float) {
        ratingBar.rating = rating / 2f
    }

    /**
     * sets the image view.
     */
    fun setImageView(path: String) {
        Glide.with(view)
            .load(path)
            .error(Glide.with(movieImage).load(R.drawable.logo))
            .into(movieImage)
    }

    /**
     * sets the releaseDate view.
     */
    fun setDateView(date:String) {
        val split = date.split("-")
        var newText = date
        if (split.size > 1) {
            newText = split[1] + "/" + split[0]
        }
        releaseDate.text = newText
    }
}