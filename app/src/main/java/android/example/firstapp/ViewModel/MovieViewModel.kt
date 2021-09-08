package android.example.firstapp.ViewModel

import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Repository.MovieRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel
@Inject
constructor(
    private val api_key: String,
    private val movieRepository: MovieRepository,
) : ViewModel() {

     val _Popular: MutableLiveData<List<Movies>> = MutableLiveData()
     val _toprated: MutableLiveData<List<Movies>> = MutableLiveData()
     val _upcoming: MutableLiveData<List<Movies>> = MutableLiveData()
     val _nowplaying: MutableLiveData<List<Movies>> = MutableLiveData()
     val _tv: MutableLiveData<List<Movies>> = MutableLiveData()
     val _trending: MutableLiveData<List<Movies>> = MutableLiveData()
    init {
        search()
    }

    fun search() {
        viewModelScope.launch {
            val resultp = movieRepository.getPopular(api_key, 1)
            val resultt = movieRepository.gettoprated(api_key, 1)
            val resultu = movieRepository.getupcoming(api_key, 1)
            val resultn = movieRepository.getnowplaying(api_key, 2)
            val resultv = movieRepository.gettv(api_key, 1)
            val trending = movieRepository.gettrending(api_key)
            _Popular.value = resultp
            _toprated.value = resultt
            _upcoming.value = resultu
            _nowplaying.value = resultn
            _tv.value = resultv
            _trending.value = trending
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("#!!!!","xcame")
    }
}