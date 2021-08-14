package huji.nati.moviebooster.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import huji.nati.moviebooster.R
import huji.nati.moviebooster.viewmodels.SingleMovieViewModel
import com.bumptech.glide.Glide
import huji.nati.moviebooster.MovieBoosterApp

class SingleMovieFragment : Fragment() {

    private val singleMovieViewModel: SingleMovieViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SingleMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signle_movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the views.
        val titleTextView : TextView = view.findViewById(R.id.titleTextView)
        val ratingNumTextView : TextView = view.findViewById(R.id.ratingNumberTextView)
        val votingRateTextView : TextView = view.findViewById(R.id.votingRateTextView)
        val releaseDateTextView : TextView = view.findViewById(R.id.releaseDateTextView)
        val overViewTextView : TextView = view.findViewById(R.id.overViewTextView)
        val goBackButton : ImageButton = view.findViewById(R.id.goBackButton)
        val imageView : ImageView = view.findViewById(R.id.imageView)
        val ratingBar : RatingBar = view.findViewById(R.id.ratingBar)


        // set the ui
        val currentMovie = singleMovieViewModel.getMovieToDisplay()
        titleTextView.text = currentMovie.title
        ratingNumTextView.text = currentMovie.vote_average.toString()
        votingRateTextView.text = currentMovie.vote_count.toString()
        releaseDateTextView.text = currentMovie.release_date
        overViewTextView.text = currentMovie.overview
        ratingBar.rating = currentMovie.vote_average
        Glide.with(view).load(MovieBoosterApp.imagesDirectoryGeneralURL +
                currentMovie.poster_path).into(imageView)

        // set onClick for the goBack button
        goBackButton.setOnClickListener {
            view.findNavController().navigate(R.id.single_movie_to_movie_list_action)
        }
    }

}