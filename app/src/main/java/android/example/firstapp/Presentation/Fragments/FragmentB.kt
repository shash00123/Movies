package android.example.firstapp.Presentation.Fragments

import android.content.Intent
import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Presentation.Activities.Detail_Activity
import android.example.firstapp.Presentation.Adapters.*
import android.example.firstapp.R
import android.example.firstapp.ViewModel.MovieViewModel
import android.example.firstapp.databinding.FragmentBBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class FragmentB :Fragment(R.layout.fragment_b),Trending_Adapter.TrendingAdapterOnClickHandler{

    private var binding: FragmentBBinding? = null
    private lateinit var trendingadapter: Trending_Adapter
    private val viewmodel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        init_recyclerview()
        adddataset()

        return super.onCreateView(inflater, container, savedInstanceState)
    }
    private fun init_recyclerview(){

    }

    private fun adddataset(){

    }

    override fun onClickT(m: Movies?) {

        val intentToStartDetailActivity = Intent(this.context, Detail_Activity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }


}