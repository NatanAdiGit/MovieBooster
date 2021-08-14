package huji.nati.moviebooster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.ui.AutocompleteItemHolder

class AutocompleteAdapter : RecyclerView.Adapter<AutocompleteItemHolder>() {
    private val movieList : MutableList<AutocompleteData> = mutableListOf()

    var onMovieClickListener : ((MovieData) -> Unit)?= null

    fun setItems(items : List<AutocompleteData>) {
        movieList.clear()
        movieList.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutocompleteItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.movie_preview_item, parent, false)
        return AutocompleteItemHolder(view)
    }

    override fun onBindViewHolder(holder: AutocompleteItemHolder, position: Int) {
        val currentAutocomplete = movieList[position]
        holder.movieName.text = currentAutocomplete.name
    }

}