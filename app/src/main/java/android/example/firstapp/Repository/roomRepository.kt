package android.example.firstapp.Repository

import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Room.MovieDao
import androidx.lifecycle.LiveData

class roomRepository(private val moviedao:MovieDao) {
    val allmovies:LiveData<List<Movies>> = moviedao.loadallMovies()
//    val flag:Int=moviedao.getflagfromdb()
    suspend fun insert(movie:Movies){
        moviedao.insertnewmovie(movie)
    }
    suspend fun deletewithtitle(title:String?){
        moviedao.deleteByTitle(title)
    }
    fun getflag(title:String?):Int{
       return  moviedao.getflagfromdb(title)
    }

    fun delete(movie: Movies):Int{
        return moviedao.deleteMovie(movie)
    }


}