package android.example.firstapp.Model.Network.Response

import android.example.firstapp.Model.Network.Model.MovieDto2
import com.google.gson.annotations.SerializedName

class MovieSearchResponse2 (
    @SerializedName("id")
    var id:Int,
    @SerializedName("results")
    var keys : List<MovieDto2>
)