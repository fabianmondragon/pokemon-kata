package com.example.data.utils

import com.example.data.responses.PokemonListResponse
import com.example.domain.models.PokemonItemEntity

class MapperDataPokemon {

    companion object {
        fun <From, To> createMapper(mappingFunction: (From) -> To): Mapper<From, To> {
            return mappingFunction
        }

        fun convertPokemonListResponseToPokemonListEntity(pokemonListResponse: PokemonListResponse): MutableList<PokemonItemEntity> {
            val pokemonListEntity : MutableList<PokemonItemEntity> = mutableListOf()
            pokemonListResponse.results.forEach{
                pokemonListEntity.add(PokemonItemEntity(name = it.name, url = it.url))
            }
            return pokemonListEntity
        }
    }


}

typealias Mapper<From, To> = (From) -> To