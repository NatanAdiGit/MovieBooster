package huji.nati.moviebooster.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.MovieListViewModel
import huji.nati.moviebooster.ui.adapters.MoviesAdapter
import huji.nati.moviebooster.R


class MovieListFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieListViewModel::class.java)
    }

    val adapter: MoviesAdapter by lazy { MoviesAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the views
        val movieRecyclerView : RecyclerView = view.findViewById(R.id.MovieListRecycleView)

        // set the adapter
        adapter.onMovieClickListener = { movieData ->
            movieListViewModel.setSingleMovieToDisplay(movieData)
            view.findNavController().navigate(R.id.movie_list_to_single_movie_action)
        }

        // set the recycle view
        movieRecyclerView.adapter = adapter
        movieRecyclerView.layoutManager =
            GridLayoutManager(requireActivity(), 2, RecyclerView.VERTICAL, false)
        movieRecyclerView.layoutManager

        // set observer to the movie list
        movieListViewModel.getMoviesListLiveData().observe(viewLifecycleOwner, Observer {
            movieListViewModel.getMoviesListLiveData().value?.let { it1 -> adapter.setItems(it1) }
        })
    }

}