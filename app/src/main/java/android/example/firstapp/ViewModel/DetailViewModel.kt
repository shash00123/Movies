package android.example.firstapp.ViewModel

import android.app.Application
import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Repository.roomRepository
import android.example.firstapp.Room.AppDatabse
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val repo: roomRepository
    val allmovies: LiveData<List<Movies>>
    var flag: Int = 0

    init {
        val dao = AppDatabse.getdatabase(application).moviedao()
        repo = roomRepository(dao)
        allmovies = repo.allmovies
        Log.i("#$#$","init")
    }

    fun addmovie(movies: Movies) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(movies)
    }

    fun deletemoviewithtitle(title: String?) = viewModelScope.launch(Dispatchers.IO) {
        repo.deletewithtitle(title)
    }

    fun getflagfromtitle(title: String?): Int {
        val a = repo.getflag(title)
        Log.i("&&&", "$a")
        return a
    }


    fun delete(movies: Movies): Int {
     return  repo.delete(movies)
    }

    override fun onCleared() {
        Log.i("#$#$","cleared")
        super.onCleared()
    }
}
