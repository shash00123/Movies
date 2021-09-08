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
class SimilarMoviesViewModel
@Inject
constructor(
    private val api_key: String,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    val similarmovies: MutableLiveData<List<Movies>> = MutableLiveData()


    fun getsimilarmovies(movieid:Int?) {
        viewModelScope.launch {
            val resultp = movieRepository.getsimilarmovies(api_key,movieid, 1)
            Log.i("infoPu","${resultp.size}")
            similarmovies.value=resultp
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("#!!!!","xcame")
    }
}