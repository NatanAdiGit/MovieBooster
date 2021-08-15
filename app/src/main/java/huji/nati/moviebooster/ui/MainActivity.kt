package huji.nati.moviebooster.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.work.WorkManager
import huji.nati.moviebooster.R
import huji.nati.moviebooster.model.MovieBoosterApp


class MainActivity : AppCompatActivity() {

    lateinit var searchImageView : ImageView
    lateinit var bachImageView : ImageView
    lateinit var homeImageView : ImageView
    lateinit var title : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchImageView = findViewById(R.id.searchIconView)
        bachImageView = findViewById(R.id.backIconView)
        homeImageView = findViewById(R.id.homeIconView)
        title = findViewById(R.id.titleText)

        title.text = MovieBoosterApp.appName
    }

}