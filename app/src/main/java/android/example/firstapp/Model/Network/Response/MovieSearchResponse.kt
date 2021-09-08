package android.example.firstapp.Model.Network.Response

import android.example.firstapp.Model.Network.Model.MovieDto
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("page")
    var page: Int,

    @SerializedName("results")
    var results: List<MovieDto>
)