package huji.nati.moviebooster.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import huji.nati.moviebooster.MovieListViewModel
import huji.nati.moviebooster.R

class StartScreenFragment : Fragment() {


    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // request for the initial movie list for display from view model
        movieListViewModel.getPopularMovieList()

        // move forwards to home screen only if we got movies to display
        movieListViewModel.getMoviesListLiveData().observe(viewLifecycleOwner, Observer {
            view.findNavController().navigate(R.id.start_to_movie_list_action)
        })
    }

}

