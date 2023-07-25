package com.example.data.utils

import com.example.data.responses.DetailPokemonResponse
import com.example.data.responses.DreamWorld
import com.example.data.responses.PokemonListResponse
import com.example.data.responses.Sprites
import com.example.domain.models.*

class MapperDataPokemon {

    companion object {
        fun <From, To> createMapper(mappingFunction: (From) -> To): Mapper<From, To> {
            return mappingFunction
        }

        fun convertPokemonListResponseToPokemonListEntity(pokemonListResponse: PokemonListResponse): MutableList<PokemonItemEntity> {
            val pokemonListEntity: MutableList<PokemonItemEntity> = mutableListOf()
            pokemonListResponse.results.forEach {
                pokemonListEntity.add(
                    PokemonItemEntity(
                        name = it.name,
                        url = it.url,
                        image = it.image
                    )
                )
            }
            return pokemonListEntity
        }

        fun convertDetailPokemonResponseToDetailPokemonEntity(detailPokemonResponse: DetailPokemonResponse): DetailPokemonResponseEntity {
            return DetailPokemonResponseEntity(
                abilities = detailPokemonResponse.abilities as List<AbilityEntity>,
                moves = detailPokemonResponse.moves as List<MoveEntity>,
                name = detailPokemonResponse.name,
                sprites = convertSpritesDataToSpriteToDomain(sprites = detailPokemonResponse.sprites),
                types = detailPokemonResponse.types as List<TypeEntity>,
                weight = detailPokemonResponse.weight
            )
        }

        private fun convertSpritesDataToSpriteToDomain(sprites: Sprites): SpritesEntity {
            return SpritesEntity(
                OtherEntity(DreamWorldEntity(sprites.other.dreamWorld.frontDefault))
            )
        }
    }


}

typealias Mapper<From, To> = (From) -> To