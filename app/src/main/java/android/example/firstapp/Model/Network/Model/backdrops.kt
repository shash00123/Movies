package android.example.firstapp.Model.Network.Model

import com.google.gson.annotations.SerializedName

data class backdrops(
    @SerializedName("file_path")
    var filepath:String,
    @SerializedName("vote_average")
    var vote:Double?=null
)
