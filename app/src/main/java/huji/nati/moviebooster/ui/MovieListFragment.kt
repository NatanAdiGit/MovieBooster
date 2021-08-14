package huji.nati.moviebooster.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.MovieListViewModel
import huji.nati.moviebooster.R


class MovieListFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieListViewModel::class.java)
    }

    private val recycleViewBundle : Bundle by lazy {Bundle()}

    private lateinit var movieRecyclerView: RecyclerView
    private var lastFirstVisiblePositionRV : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the views
        movieRecyclerView = view.findViewById(R.id.MovieListRecycleView)

        // set the adapter
        movieListViewModel.adapter.onMovieClickListener = { movieData ->
            movieListViewModel.setMovieToDisplay(movieData)
            view.findNavController().navigate(R.id.movie_list_to_single_movie_action)

        }

        movieRecyclerView.adapter = movieListViewModel.adapter

        // set the layout
        movieRecyclerView.layoutManager =
            GridLayoutManager(requireActivity(), 2, RecyclerView.VERTICAL, false)
        movieRecyclerView.layoutManager

    }

//    override fun onStop() {
//        super.onStop()
//        lastFirstVisiblePositionRV =
//            (movieRecyclerView.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        lastFirstVisiblePositionRV =
//            (movieRecyclerView.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
//
//    }

//    override fun onPause() {
//        super.onPause()
//        val recycleViewState = movieRecyclerView.layoutManager?.onSaveInstanceState()
//        recycleViewBundle.putParcelable("recycle_view_position", recycleViewState)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        val recycleViewState = recycleViewBundle.getParcelable("recycle_view_position")
//        mRecyclerView.getLayoutManager().onRestoreInstanceState(mListState)
//        mRecyclerView.setLayoutManager(staggeredGridLayoutManager)
//
//    }



}