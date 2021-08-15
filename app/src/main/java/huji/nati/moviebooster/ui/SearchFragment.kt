package huji.nati.moviebooster.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.R
import huji.nati.moviebooster.model.AutocompleteData
import huji.nati.moviebooster.viewmodels.SearchViewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
    }

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find views
        val searchView: SearchView = view.findViewById(R.id.search_bar) as SearchView
        val listView: ListView = view.findViewById(R.id.lvListView)

        // set adapter
        adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, arrayListOf())
        listView.adapter = adapter

        searchViewModel.getAutocompleteLiveData().observe(viewLifecycleOwner, Observer
        {
            addAutocompleteToAdapter(searchViewModel.getAutocompleteLiveData().value)
        })

        //search_to_movie_list_action

        listView.setOnItemClickListener { _, _, position, _ ->
            val movieName : String = listView.getItemAtPosition(position).toString()
            searchViewModel.getSearchMovieByQuery(movieName)
            view.findNavController().navigate(R.id.search_to_movie_list_action)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.isNotEmpty()) {
                    if (newText.substring(newText.length - 1) == " ") {
                        searchViewModel.getAutoCompleteByQuery(newText)
                        Log.e("dkjsjds", "dkdjf")
                    }
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    searchViewModel.getSearchMovieByQuery(query)
                    view.findNavController().navigate(R.id.search_to_movie_list_action)
                }
                return false
            }
        })
    }
//
//    override fun onDestroyView() {
//        val activity = activity as? MainActivity
//        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        setHasOptionsMenu(false)
//        super.onDestroyView()
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val activity = activity as? MainActivity
//        return when (item.itemId) {
//            android.R.id.home -> {
//                requireActivity().onBackPressed()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    private fun addAutocompleteToAdapter(lst :List<AutocompleteData>?) {
        if (lst == null)
            return
        adapter.clear()
        for (auto in lst) {
            adapter.add(auto.name)
        }
        adapter.setNotifyOnChange(true)
    }


}