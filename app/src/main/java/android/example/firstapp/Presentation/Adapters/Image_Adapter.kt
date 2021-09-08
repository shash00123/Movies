package android.example.firstapp.Presentation.Adapters

import android.content.Context
import android.content.Intent
import android.example.firstapp.Presentation.Activities.Zoom_Activity
import android.example.firstapp.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Image_Adapter(
    c: Context,
//    images: List<String>,
) : RecyclerView.Adapter<Image_Adapter.MyViewHolder>() {
    private var ls: List<String> = ArrayList()
    private var context: Context? = null

    init {
//        ls = images
        context = c
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView

        init {
            // get the reference of item view's
            image = itemView.findViewById<View>(R.id.iconbruhh) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_grid_view, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val Imgurl = "https://image.tmdb.org/t/p/w500" + ls[position]
       Log.i("!@#","${ls.size}")
        Picasso.get().load(Imgurl).into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Zoom_Activity::class.java)
            intent.putExtra("selectedimage", ls[position])
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ls.size
    }
    fun submitlist(list:List<String>){
        Log.i("@@@@@","ADAPTER CALLEd")
        ls=list
        notifyDataSetChanged()
    }

}