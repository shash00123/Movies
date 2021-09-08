package android.example.firstapp

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.example.firstapp.Presentation.Fragments.FragmentA
import android.example.firstapp.Presentation.Fragments.FragmentB
import android.example.firstapp.Presentation.Fragments.FragmentC
import android.example.firstapp.Presentation.Activities.SearchableActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

    private lateinit var bn: BottomNavigationView
    private var SelectedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val t = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(t);
        bn = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bn.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    val fragment = FragmentA()
                    supportActionBar?.title=resources.getString(R.string.app_name)

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_View, fragment, fragment.javaClass.simpleName)
                        .commit()
                    true
                }
                R.id.navigation_explore -> {
                    Log.i("~!@", "CAME!!!!!!!!!!!!!!!!!!")
                    val fragment = FragmentB()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_View, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    true
                }
                R.id.navigation_new -> {

                    val fragment = FragmentC()
                    Log.i("Q@@", "Pressed")
                    supportActionBar?.title="Watchlist"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_View, fragment, fragment.javaClass.getSimpleName())
                        .commit()

                    true
                }
                else -> false
            }
        }
        bn.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                }
                R.id.navigation_explore -> {
                }
                R.id.navigation_new -> {
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            val fragment =
                supportFragmentManager.findFragmentByTag(FragmentA::class.java.simpleName)
                    ?: FragmentA()
            Log.i("~!@", "CAME!!!!!!!!!!!!!!!!!!")

            supportFragmentManager.beginTransaction()
                .replace(R.id.container_View, fragment, FragmentA::class.java.simpleName)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView
        val cn=ComponentName(this,SearchableActivity::class.java)
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(cn))
        Log.i("@!@!",""+searchManager.getSearchableInfo(cn)+"f")
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id=item.itemId
//        if(id==R.id.search){
//            Log.i("@#@#","))))))))))))))")
//
//            }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("detached1", "mainactivity")
    }


}