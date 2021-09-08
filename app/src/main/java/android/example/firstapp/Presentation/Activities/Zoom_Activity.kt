package android.example.firstapp.Presentation.Activities

import android.example.firstapp.R
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class Zoom_Activity :AppCompatActivity() {
    var selectedImage: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zoom_activity)
        selectedImage = findViewById<View>(R.id.selectedImage) as ImageView // init a ImageView
        val intent = intent // get Intent which we set from Previous Activity
        val Imgurl = "https://image.tmdb.org/t/p/w500" + intent.getStringExtra("selectedimage")
        Picasso.get().load(Imgurl).resize(550, 550).into(selectedImage)

    }
}