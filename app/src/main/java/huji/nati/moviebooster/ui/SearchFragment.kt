package huji.nati.moviebooster.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.R
import huji.nati.moviebooster.model.AutocompleteData
import huji.nati.moviebooster.viewmodels.SearchViewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
    }

    private lateinit var adapter: ArrayAdapter<*>

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
//        val autoRecycleView: RecyclerView = view.findViewById(R.id.autoCompleteRecycleView)

        searchViewModel.getAutocompleteLiveData().observe(viewLifecycleOwner, Observer
        {
            adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1,
                getStringListFromAutoList(searchViewModel.getAutocompleteLiveData().value))
            listView.adapter = adapter
        })

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
//                if (query != null && query.isNotEmpty()) {
//                    if (query.substring(query.length - 1) == " ") {
//                        searchViewModel.getAutoCompleteByQuery(query)
//                        Log.e("dkjsjds", "dkdjf")
//                    }
//                }

                return false
            }
        })
        val emptyList: List<AutocompleteData> = listOf()
    }

    fun getStringListFromAutoList(lst :List<AutocompleteData>?) : List<String> {
        if (lst == null)
            return listOf()
        var newList : ArrayList<String> = arrayListOf()
        for (auto in lst) {
            newList.add(auto.name)
        }
        return newList
    }

}