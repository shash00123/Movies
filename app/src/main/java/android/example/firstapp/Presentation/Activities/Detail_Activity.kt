package android.example.firstapp.Presentation.Activities

//import android.example.firstapp.Room.AppDatabse
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Model.Network.Model.MovieDtoMapper
import android.example.firstapp.Presentation.Adapters.Trending_Adapter
import android.example.firstapp.R
import android.example.firstapp.ViewModel.DetailViewModel
import android.example.firstapp.ViewModel.SimilarMoviesViewModel
import android.example.firstapp.databinding.DetailActivityBinding
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class Detail_Activity : AppCompatActivity(), Trending_Adapter.TrendingAdapterOnClickHandler {
    //    private val Flag = 0
    private val list: List<String> = ArrayList()
    var Titled: String? = null
    var Imaged: String? = null
    var Ratingd: String? = null
    var backimaged: String? = null
    var infod: String? = null
    var movieidd: Int? = null
    private lateinit var binding: DetailActivityBinding
    lateinit var viewmodel: DetailViewModel
    private var flag: Int? = null
    private lateinit var t1: Toast
    private lateinit var trendingadapter: Trending_Adapter
    private val viewmodels: SimilarMoviesViewModel by viewModels()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentthatstartedthisactivity: Intent = intent
        val m: Movies? = intentthatstartedthisactivity.getParcelableExtra("parcea")
        Titled = m?.Title
        Imaged = m?.Poster
        Ratingd = m?.Vote
        backimaged = m?.BackImage
        infod = m?.Overview
        movieidd = m?.Movieid
        Log.i("idbhai", "${movieidd}")
        viewmodel = ViewModelProvider(this).get(DetailViewModel::class.java)
        t1 = Toast.makeText(this,
            "Sorry not available for now",
            Toast.LENGTH_SHORT)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        binding.rent25.setOnClickListener {
            t1.show()
        }
        setWatchlistState();
        setImages()
        setInfo()
        setWatchList()
        setSimilarMovies()
        binding.TitleFr.setText(Titled)
        binding.ratingFr.text = Ratingd
        binding.trailerFr.setOnClickListener {
            val intentTStartDetailActivity = Intent(this, Trailer_Activity::class.java)
            intentTStartDetailActivity.putExtra(Intent.EXTRA_TEXT, movieidd)
            startActivity(intentTStartDetailActivity)
        }

//        binding.imageButton.setOnClickListener {
//            val intent = Intent(this, Image_Activity::class.java)
//            intent.putExtra("ID", movieidd)
//            startActivity(intent)
//        }

    }

    private fun setWatchlistState() {
        GlobalScope.launch(Dispatchers.IO) {
            flag = viewmodel.getflagfromtitle(Titled)
            if (flag == 0) {
                runOnUiThread {
                    binding.watchlistFr.text = "Add to watchlist"
                    binding.watchlistFr.setTextColor(resources.getColor(R.color.white))
                }
            } else {
                runOnUiThread {
                    binding.watchlistFr.text = "Added to watchlist"
                    binding.watchlistFr.setTextColor(resources.getColor(R.color.Red))
                }
            }
        }
    }

    private fun setImages() {
        val url = "https://image.tmdb.org/t/p/w500" + Imaged
        Picasso.get().load(url).resize(180, 260).into(binding.posterFr)

        val title = "https://image.tmdb.org/t/p/w500" + backimaged
        Picasso.get().load(title).resize(1610, 810).into(binding.backgroundImageFr)
    }

    private fun setInfo() {
        var overview = infod
        var extra: String? = null
        if (overview != null) {
            if (overview.length > 90) {
                extra = overview.substring(90, overview.length)
                overview = overview.substring(0, 90) + "..."
            }
            binding.overviewFr.setText(overview)
        }
        if (extra != null) {
            binding.moreFr.setOnClickListener {
                if (binding.moreFr.text.toString().equals("More")) {
                    binding.overviewFr.setText(overview?.substring(0, 90) + extra)
                    binding.moreFr.text = "Less"
                } else {
                    binding.overviewFr.setText(overview)
                    binding.moreFr.text = "More"
                }
            }
        } else {
            binding.moreFr.visibility = View.INVISIBLE
        }
    }

    private fun setWatchList() {
        binding.watchlistFr.setOnClickListener {
            if (binding.watchlistFr.currentTextColor == resources.getColor(R.color.white)) {
                viewmodel.addmovie(Movies(flag = 1,
                    Movieid = movieidd,
                    Title = Titled,
                    Vote = Ratingd,
                    Overview = infod,
                    BackImage = backimaged,
                    Poster = Imaged))
                binding.watchlistFr.text = "Added To watchlist"
                binding.watchlistFr.setTextColor(resources.getColor(R.color.Red))
            } else {
                viewmodel.deletemoviewithtitle(Titled)
                binding.watchlistFr.text = "Add To watchlist"
                binding.watchlistFr.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

private fun setSimilarMovies(){
    binding.recyclerviewSimilar.apply {
        layoutManager =
            LinearLayoutManager(this@Detail_Activity, RecyclerView.HORIZONTAL, false)
        trendingadapter = Trending_Adapter(this@Detail_Activity)
        adapter = trendingadapter
    }
    viewmodels.getsimilarmovies(movieidd)
    viewmodels.similarmovies.observe(this, { Movies ->
        if (Movies.size != 0) {
            Log.i("response", "${Movies?.get(0)?.Title}")
            trendingadapter.submitlist(Movies)

        } else {
            binding.similarTextview.visibility = View.GONE
        }

    })
}









    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.nothing, R.anim.slide_out)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView
        val cn = ComponentName(this, SearchableActivity::class.java)
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(cn))
        Log.i("@!@!", "" + searchManager.getSearchableInfo(cn) + "f")
        return super.onCreateOptionsMenu(menu)

    }

    override fun onClickT(m: Movies?) {
        val intentToStartDetailActivity = Intent(this, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }
}