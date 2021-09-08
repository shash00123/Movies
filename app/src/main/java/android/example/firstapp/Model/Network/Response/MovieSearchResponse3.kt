package android.example.firstapp.Model.Network.Response

import android.example.firstapp.Model.Network.Model.backdrops
import com.google.gson.annotations.SerializedName

class MovieSearchResponse3 (
    @SerializedName("backdrops")
    var images:List<backdrops>
)