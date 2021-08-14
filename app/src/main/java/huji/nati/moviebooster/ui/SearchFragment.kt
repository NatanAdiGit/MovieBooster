package huji.nati.moviebooster.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.MovieListViewModel
import huji.nati.moviebooster.R
import huji.nati.moviebooster.viewmodels.SearchViewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find views
        val searchView : SearchView = view.findViewById(R.id.search_bar) as SearchView
        val autoRecycleView : RecyclerView= view.findViewById(R.id.autoCompleteRecycleView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    if (query.substring(query.length - 1) == " ") {
                        searchViewModel.updatesAutoCompleteOptions(query)
                    }
                }

                return false
            }

        })

        movieListViewModel.getPopularMoviesList()
        view.findNavController().navigate(R.id.start_to_movie_list_action)
        movieListViewModel.displayedMoviesLiveData.observe(viewLifecycleOwner, Observer {
            view.findNavController().navigate(R.id.start_to_movie_list_action)
        })
    }


}