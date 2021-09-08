package android.example.firstapp.Presentation.Activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Presentation.Adapters.Trending_Adapter
import android.example.firstapp.R

import android.example.firstapp.ViewModel.TrailerViewModel
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchableActivity : AppCompatActivity(), Trending_Adapter.TrendingAdapterOnClickHandler {
    private lateinit var trendingadapter: Trending_Adapter
    private val viewmodel: TrailerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchable)
        init_recyclerview()
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                viewmodel.getmovie(query)
                val t=findViewById<TextView>(R.id.search_textView)
                t.setText(query)
                add_dataset()
            }
        }

    }

    private fun init_recyclerview() {
        val r = findViewById<RecyclerView>(R.id.search_recycler_view)
        r.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        trendingadapter = Trending_Adapter(this)
        r.adapter = trendingadapter
    }

    private fun add_dataset() {
       viewmodel.movie.observe(this,{
           trendingadapter.submitlist(it)
       })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClickT(m: Movies?) {
        val intentToStartDetailActivity = Intent(this, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }
}