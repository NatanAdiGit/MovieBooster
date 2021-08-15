package huji.nati.moviebooster.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import huji.nati.moviebooster.model.AutocompleteData
import huji.nati.moviebooster.model.MovieBoosterApp


class SearchViewModel : ViewModel() {

    private val mainApp by lazy { MovieBoosterApp.instance}

    fun getAutocompleteLiveData() : LiveData<List<AutocompleteData>> {
        return mainApp.autocompleteLiveData
    }

    fun getAutoCompleteByQuery(query : String) {
        mainApp.requestAutocompleteByQuery(query)
    }

}