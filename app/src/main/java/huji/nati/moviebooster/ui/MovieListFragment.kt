package huji.nati.moviebooster.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.MovieListViewModel
import huji.nati.moviebooster.R

class MovieListFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the views.
        val itemsRecycler: RecyclerView = view.findViewById(R.id.MovieListRecycleView)
        itemsRecycler.adapter = movieListViewModel.adapter
        itemsRecycler.layoutManager =
            GridLayoutManager(requireActivity(), 2, RecyclerView.VERTICAL, false)
        itemsRecycler.layoutManager

    }

}