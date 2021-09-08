package android.example.firstapp.Presentation.Fragments

import android.content.Intent
import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.ViewModel.MovieViewModel
import android.example.firstapp.Presentation.Activities.Detail_Activity
import android.example.firstapp.Presentation.Adapters.*
import android.example.firstapp.R
//import android.example.firstapp.ViewModelFactoy
import android.example.firstapp.databinding.FragmentABinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentA() : Fragment(R.layout.fragment_a), Top_rated_Adapter.TopratedAdapterOnClickHandler,
    Now_playing_Adapter.NowplayingAdapterOnClickHandler,
    Trending_Adapter.TrendingAdapterOnClickHandler, Popular_Adapter.PopularAdapterOnClickHandler,
    Upcoming_Adapter.UpcomingAdapterOnClickHandler ,Tv_Adapter.Tv_AdapterOnclickHandler{

    private var binding: FragmentABinding? = null
    private lateinit var padapter: Popular_Adapter
    private lateinit var uadapter: Upcoming_Adapter
    private lateinit var tadapter: Top_rated_Adapter
    private lateinit var nadapter: Now_playing_Adapter
    private lateinit var tvadapter: Tv_Adapter
    private lateinit var trendingadapter: Trending_Adapter
    private val viewmodel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.i("cameyo","d")
        binding = FragmentABinding.inflate(inflater, container, false)
        val view = binding!!.root
        init_recyclerview()
        adddataset()
        return view
    }

    private fun init_recyclerview() {
        binding?.p?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            trendingadapter = Trending_Adapter(this@FragmentA)
            adapter = trendingadapter
        }

        binding?.u?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            padapter = Popular_Adapter(this@FragmentA)
            adapter = padapter
        }
        binding?.u2?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            nadapter = Now_playing_Adapter(this@FragmentA)
            adapter = nadapter
        }

        binding?.u3?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            uadapter = Upcoming_Adapter(this@FragmentA)
            adapter = uadapter
        }
        binding?.u4?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            tadapter = Top_rated_Adapter(this@FragmentA)
            adapter = tadapter
        }
        binding?.u5?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            tvadapter = Tv_Adapter(this@FragmentA)
            adapter = tvadapter
        }

    }

    private fun adddataset() {
        Log.i("@@@@@", "CALLIG ADAPTER")
        viewmodel._trending.observe(viewLifecycleOwner, { Movies ->
//            binding?.progress?.visibility=View.VISIBLE
            trendingadapter.submitlist(Movies)
            binding?.trending?.visibility = View.VISIBLE
            binding?.trending1?.visibility=View.VISIBLE
        })
        viewmodel._Popular.observe(viewLifecycleOwner, { Movies ->
            padapter.submitlist(Movies)
            binding?.popularT?.visibility = View.VISIBLE
            binding?.popular1?.visibility = View.VISIBLE

        })
        viewmodel._toprated.observe(viewLifecycleOwner, { Movies ->
            tadapter.setToprateddata(Movies)
            binding?.topratedT?.visibility = View.VISIBLE
        })
        viewmodel._upcoming.observe(viewLifecycleOwner, { Movies ->
            uadapter.setUpcomingdata(Movies)
            binding?.upcomingT?.visibility = View.VISIBLE
        })
        viewmodel._nowplaying.observe(viewLifecycleOwner, { Movies ->
            nadapter.submitlist(Movies)
            binding?.nowPlaying?.visibility = View.VISIBLE
        })
        viewmodel._tv.observe(viewLifecycleOwner, { Movies ->
            tvadapter.submitlist(Movies)
            binding?.tv?.visibility = View.VISIBLE
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        Log.i("detached", "dff")
    }

    override fun onClickt(m: Movies?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onClickN(m: Movies?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onClickT(m: Movies?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onClickp(m: Movies?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onClicku(m: Movies?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onclick(m: Movies?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }
}