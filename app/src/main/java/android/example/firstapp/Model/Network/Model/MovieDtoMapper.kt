package android.example.firstapp.Model.Network.Model

import android.example.firstapp.Model.Domain.Model.Movies
import android.example.firstapp.Model.Domain.Util.DomainMapper

class MovieDtoMapper : DomainMapper<MovieDto, Movies> {
    override fun mapFromDomainModel(domainModel: Movies): MovieDto {
        return MovieDto(
            domainModel.Movieid,
            domainModel.Title,
            domainModel.Poster,
            domainModel.BackImage,
            domainModel.Vote,
            domainModel.Overview
        )
    }

    override fun mapToDomainModel(model: MovieDto): Movies {
     val title=if(model.title==null) model.name else model.title
       return  Movies(
           0,0,
          model.id,
          title ,
           model.vote_average,
           model.overview,
           model.backdrop_path,
           model.poster_path
       )
    }
    fun toDomainList(initial: List<MovieDto>): List<Movies>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Movies>): List<MovieDto>{
        return initial.map { mapFromDomainModel(it) }
    }

}