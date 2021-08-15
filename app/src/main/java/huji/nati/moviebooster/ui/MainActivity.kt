package huji.nati.moviebooster.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import huji.nati.moviebooster.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        // showing the back button in action bar
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater : MenuInflater = menuInflater
//        inflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id: Int = item.itemId
//        if (id == R.id.searchButton) {
//            val
//            val navController = findNavController(R.id.nav_host_fragment)
//            navController.navigateUp()
//            navController.navigate(R.id.searchFragment)
//            return true
//        }
//        if (id == R.id.searchButton) {
//            onBackPressed()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
}