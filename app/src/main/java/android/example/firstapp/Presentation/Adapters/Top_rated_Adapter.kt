package android.example.firstapp.Presentation.Adapters

import android.content.Context
import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.toprated_xml.view.*

class Top_rated_Adapter(private var clickhandler:TopratedAdapterOnClickHandler) :
    RecyclerView.Adapter<Top_rated_Adapter.TopratedAdapterViewHolder>() {

    private var movies: List<Movies>? = null
    private var mClickHandler: TopratedAdapterOnClickHandler? = null
    init{
        mClickHandler=clickhandler
    }

    interface TopratedAdapterOnClickHandler {
        fun onClickt(m: Movies?)
    }


    inner class TopratedAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val image: ImageView = itemView.blog_image
        val Titl: TextView =itemView.Title
        var rate:TextView=itemView.rate
        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            mClickHandler?.onClickt(movies?.get(adapterPosition))
        }


        init {
            Log.i("----------", "ViewHolderConstructor")
            itemView.setOnClickListener(this)
        }
    }

    override fun getItemCount(): Int {
        Log.i("QQQQ","d")
        if (movies == null) {
            return 0
        }
        return movies!!.size
    }

    override fun onBindViewHolder(holder: TopratedAdapterViewHolder, position: Int) {
        val Title: String? = movies?.get(position)?.Title
        Log.i("QQQQQ", "" + Title)
        holder.Titl.text = Title
        val Imgurl =
            "https://image.tmdb.org/t/p/w500" + movies?.get(position)?.Poster
        holder.rate.text=movies?.get(position)?.Vote
        if (movies?.get(position)?.Poster != null) {
            Picasso.get().load(Imgurl).resize(300, 420).into(holder.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopratedAdapterViewHolder {
        Log.i("@@","hmm")
        val context: Context = parent.context
        val layoutIdForListItem: Int = R.layout.toprated_xml
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        return TopratedAdapterViewHolder(view)
    }

    fun setToprateddata(toprateddata: List<Movies>?) {
        Log.i("camebruh", "d" + toprateddata?.get(0)?.Title)
        movies = toprateddata
        notifyDataSetChanged()
    }
}