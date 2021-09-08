package android.example.firstapp.Presentation.Activities

import android.example.firstapp.Presentation.Adapters.Image_Adapter
import android.example.firstapp.R
import android.example.firstapp.ViewModel.TrailerViewModel
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Image_Activity :AppCompatActivity(){

    private var rv: RecyclerView? = null
    private var ls = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_activitiy)
        rv = findViewById<View>(R.id.recyclerview_image) as RecyclerView
        val intent = intent
        val c = Image_Adapter(this)


        val gridLayoutManager =
            GridLayoutManager(applicationContext, 3, RecyclerView.VERTICAL, false)
        rv!!.layoutManager = gridLayoutManager
        rv!!.adapter = c

        val viewmodel = ViewModelProviders.of(this).get(TrailerViewModel::class.java)
        viewmodel.getimages(intent.getIntExtra("ID",0))
        viewmodel.images.observe(this,{images->
            for(i in images){
            ls.add(i.filepath)
                Log.i("QWER",i.filepath)
            }
           c.submitlist(ls)

        })

    }
}