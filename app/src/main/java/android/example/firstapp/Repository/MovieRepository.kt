package android.example.firstapp.Repository

import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Model.Network.Model.MovieDto2
import android.example.firstapp.Model.Network.Model.backdrops

interface MovieRepository {
    suspend fun getPopular(key: String, page: Int): List<Movies>
    suspend fun getupcoming(key: String, page: Int): List<Movies>
    suspend fun gettoprated(key: String, page: Int ): List<Movies>
    suspend fun getnowplaying(key: String, page: Int ): List<Movies>
    suspend fun gettv(key: String, page: Int ): List<Movies>
    suspend fun gettrending(key: String): List<Movies>
    suspend fun getkey(id:Int,key: String): List<MovieDto2>
    suspend fun getimages(id:Int,key: String): List<backdrops>
    suspend fun getmovie(key: String,query:String):List<Movies>
    suspend fun getsimilarmovies(key: String,id:Int?,page:Int):List<Movies>


}