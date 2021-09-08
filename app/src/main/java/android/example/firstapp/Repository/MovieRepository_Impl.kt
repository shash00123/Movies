package android.example.firstapp.Repository

import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Model.Network.Model.MovieDto2
import android.example.firstapp.Model.Network.Model.MovieDtoMapper
import android.example.firstapp.Model.Network.Model.backdrops
import android.example.firstapp.Model.Network.MovieService
import android.example.firstapp.Model.Network.Response.MovieSearchResponse
import retrofit2.Response

class MovieRepository_Impl
constructor(
    private val MovieService: MovieService,
    private val mapper: MovieDtoMapper,
) : MovieRepository {

    override suspend fun getPopular(key: String, page: Int): List<Movies> {
        return mapper.toDomainList(MovieService.getPopular(key, page).results)
    }

    override suspend fun getupcoming(key: String, page: Int): List<Movies> {
        return mapper.toDomainList(MovieService.getupcoming(key, page).results)
    }

    override suspend fun gettoprated(key: String, page: Int): List<Movies> {
        return mapper.toDomainList(MovieService.gettoprated(key, page).results)
    }

    override suspend fun getnowplaying(key: String, page: Int): List<Movies> {
        return mapper.toDomainList(MovieService.getnowplaying(key,page).results)
    }

    override suspend fun gettv(key: String, page: Int): List<Movies> {
        return mapper.toDomainList(MovieService.getlatest(key,page).results)
    }

    override suspend fun gettrending(key: String): List<Movies> {
        return  mapper.toDomainList(MovieService.gettrending(key).results)
    }

    override suspend fun getkey(id: Int, key: String): List<MovieDto2> {
      return   MovieService.getKey(id, key).keys
    }

    override suspend fun getimages(id: Int, key: String): List<backdrops> {
        return MovieService.getimages(id, key).images
    }

    override suspend fun getmovie(key: String, query: String): List<Movies> {
       return mapper.toDomainList(MovieService.getmovie(key, query).results)
    }

    override suspend fun getsimilarmovies(key: String, id: Int?, page: Int): List<Movies> {
        if(MovieService.getsimilar(id,key,page).isSuccessful){
            return mapper.toDomainList(MovieService.getsimilar(id,key, page).body()!!.results);
        }
        val l:List<Movies> = listOf()
        return l;
    }


}