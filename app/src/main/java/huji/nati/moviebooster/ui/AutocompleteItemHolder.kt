package huji.nati.moviebooster.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import huji.nati.moviebooster.R

class AutocompleteItemHolder (private val view: View) : RecyclerView.ViewHolder(view){

    val movieName : TextView = view.findViewById(R.id.contentTextView)
}