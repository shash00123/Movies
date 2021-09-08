package android.example.firstapp.Presentation.Fragments

import android.content.Intent
import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Presentation.Activities.Detail_Activity
import android.example.firstapp.Presentation.Adapters.Fragment_c_Adapter
import android.example.firstapp.R
import android.example.firstapp.ViewModel.DetailViewModel
import android.example.firstapp.databinding.FragmentCBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragmentC : Fragment(R.layout.fragment_c), Fragment_c_Adapter.FragmentcOnClickHandler {

    private var binding: FragmentCBinding? = null
    private lateinit var padapter: Fragment_c_Adapter
    lateinit var viewmodel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCBinding.inflate(inflater, container, false)
        val view = binding!!.root
        binding?.pbLoading?.visibility = View.VISIBLE

        viewmodel = ViewModelProvider(this).get(DetailViewModel::class.java)

        init_recyclerview()
        adddataset()

        return view
    }

    private fun init_recyclerview() {
        binding?.recyclerviewFragmentc?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            padapter = Fragment_c_Adapter(this@FragmentC)
            adapter = padapter
        }
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                //Remove swiped item from list and notify the RecyclerView
                val position = viewHolder.adapterPosition

                GlobalScope.launch {
                    viewmodel.delete(padapter.getmovie().get(position))

                }
                Toast.makeText(activity?.applicationContext, "Deleted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding?.recyclerviewFragmentc)

    }

    private fun adddataset() {
        viewmodel.allmovies.observe(viewLifecycleOwner, {
            padapter.submitlist(it)
            binding?.pbLoading?.visibility = View.INVISIBLE
        })
    }


    override fun onClickp(m: Movies?) {
        val intentToStartDetailActivity = Intent(this.context, Detail_Activity::class.java);
        intentToStartDetailActivity.putExtra("parcea", m);
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)

    }
}