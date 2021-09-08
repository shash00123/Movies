package android.example.firstapp.Room

import android.example.firstapp.Model.Domain.Model.Movies
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MovieDao  {
    @Query("SELECT * FROM Movie ")
    fun loadallMovies(): LiveData<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertnewmovie(movie: Movies?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movies?)

    @Delete
    fun deleteMovie(movie: Movies?): Int

    @Query("DELETE FROM Movie WHERE Title = :title")
    fun deleteByTitle(title: String?): Int

    @Query("SELECT flag FROM Movie Where Title=:title")
    fun getflagfromdb(title: String?): Int
}