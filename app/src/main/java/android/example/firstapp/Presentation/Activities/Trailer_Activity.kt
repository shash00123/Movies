package android.example.firstapp.Presentation.Activities

import android.content.Intent
import android.example.firstapp.Model.Network.Model.MovieDto2
import android.example.firstapp.R
import android.example.firstapp.ViewModel.TrailerViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Trailer_Activity : AppCompatActivity() {
    private lateinit var yp: YouTubePlayerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trailer_xml)
        yp = findViewById(R.id.yplayer)
        getLifecycle().addObserver(yp);
        val intent = intent
        val id = intent.getIntExtra(Intent.EXTRA_TEXT, 0)
        val viewmodel = ViewModelProviders.of(this).get(TrailerViewModel::class.java)
        viewmodel.getKey(id)
        viewmodel.key.observe(this, { M ->
            val key=findTrailer(M)
            if(key!=null) {
                yp.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(key, 0F)
                    }
                })
            }else{
                Toast.makeText(this,"Trailer Not available ",Toast.LENGTH_LONG).show()
                Toast.makeText(this,"Please go back ",Toast.LENGTH_LONG).show()

            }
        })
    }

    private fun findTrailer(M: List<MovieDto2>): String? {
       var key:String?=null
        if(M.size==0){
            return null
        }
        if(M.size==1){
            key=M.get(0).key
        }else{
            for(i in M){
                if(i.type=="Trailer"){
                    key=i.key
                    break
                }
            }
        }
        return key
    }

    override fun onDestroy() {
        super.onDestroy()
        yp.release()
    }
}