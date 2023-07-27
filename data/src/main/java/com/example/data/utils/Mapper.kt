package com.example.data.utils

import com.example.data.responses.*
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
                        image = it.image,
                        id = getId(it.url)
                    )
                )
            }
            return pokemonListEntity
        }

        private fun getId(url: String): String {
            return url.split("/".toRegex()).dropLast(1).last()
        }

        fun convertDetailPokemonResponseToDetailPokemonEntity(detailPokemonResponse: DetailPokemonResponse): DetailPokemonResponseEntity {
            return DetailPokemonResponseEntity(
                abilities = convertListAbilitiesToListAbilitiesDomain(detailPokemonResponse.abilities),
                moves = convertListMoveToListMoveEntity(detailPokemonResponse.moves),
                name = detailPokemonResponse.name,
                sprites = convertSpritesDataToSpriteToDomain(sprites = detailPokemonResponse.sprites),
                types = convertListTypeToListTypeEntity(detailPokemonResponse.types),
                weight = detailPokemonResponse.weight,
                id = detailPokemonResponse.id
            )
        }

        private fun convertListTypeToListTypeEntity(types: List<Type>): MutableList<TypeEntity> {
            val listTypeEntity: MutableList<TypeEntity> = mutableListOf()
            types.forEach {
                listTypeEntity.add(convertTypesPokemonToTypesEntity(it))
            }
            return listTypeEntity
        }

        private fun convertTypesPokemonToTypesEntity(type: Type) =
            TypeEntity(slot = type.slot, type = convertTypeDetailToTypeDetailEntity(type.type))


        private fun convertTypeDetailToTypeDetailEntity(typeDetail: TypeDetail) =
            TypeDetailEntity(name = typeDetail.name, url = typeDetail.url)


        private fun convertListMoveToListMoveEntity(move: List<Move>): List<MoveEntity> {
            val listMove: MutableList<MoveEntity> = mutableListOf()
            move.forEach {
                listMove.add(convertMoveToMoveEntity(it))
            }
            return listMove
        }

        private fun convertMoveToMoveEntity(move: Move) =
            MoveEntity(convertMoveDetailToMoveDetailEntity(move.move))

        private fun convertMoveDetailToMoveDetailEntity(moveDetail: MoveDetail) =
            MoveDetailEntity(name = moveDetail.name, url = moveDetail.url)


        private fun convertListAbilitiesToListAbilitiesDomain(abilities: List<Ability>): List<AbilityEntity> {
            val listAbilities: MutableList<AbilityEntity> = mutableListOf()
            abilities.forEach {
                listAbilities.add(convertAbilityToAbilityEntity(it.ability))
            }
            return listAbilities
        }

        private fun convertAbilityToAbilityEntity(ability: AbilityDetail) =
            AbilityEntity(ability = convertAbilityDetailToAbilityDetailEntity(ability))

        private fun convertAbilityDetailToAbilityDetailEntity(abilityDetail: AbilityDetail) =
            run { AbilityDetailEntity(name = abilityDetail.name, url = abilityDetail.url) }


        private fun convertSpritesDataToSpriteToDomain(sprites: Sprites): SpritesEntity {
            return SpritesEntity(
                OtherEntity(DreamWorldEntity(sprites.other.dreamWorld.frontDefault))
            )
        }

        fun convertToEvolutionChainResponseToEvolutionChainEntity(evolutionChainResponse: EvolutionChainResponse): EvolutionChainEntity {
            return EvolutionChainEntity(chain = convertChainToChainEntity(evolutionChainResponse.chain))
        }

        private fun convertChainToChainEntity(chain: Chain): ChainEntity {
            return ChainEntity(evolvesTo = convertListEvolvesToToListEvolvesEntity(chain.evolvesTo))
        }

        private fun convertEvolvesToEvolvesToEntity(evolvesTo: EvolvesTo): EvolvesToEntity {
            return EvolvesToEntity(
                evolvesTo = evolvesTo.evolvesTo?.let { convertListEvolvesToToListEvolvesEntity(it) },
                species = convertSpeciesToSpeciesEntity(evolvesTo.species)
            )
        }

        private fun convertListEvolvesToToListEvolvesEntity(listEvolvesTo: List<EvolvesTo>): MutableList<EvolvesToEntity> {
            val listEvolvesToEntity: MutableList<EvolvesToEntity> = mutableListOf()
            listEvolvesTo.forEach {
                listEvolvesToEntity.add(convertEvolvesToEvolvesToEntity(it))
            }
            return listEvolvesToEntity
        }

        private fun convertSpeciesToSpeciesEntity(species: Species): SpeciesEntity {
            return SpeciesEntity(species.name)
        }

        fun convertListLocationResponseToListLocationEntity(listLocationResponse: List<LocationResponse>): MutableList<LocationAreaEntity> {
            val listLocationEntity: MutableList<LocationAreaEntity> = mutableListOf()
            listLocationResponse.forEach {
                listLocationEntity.add(convertLocationAreaToLocationAreaEntity(it.locationArea))
            }
            return listLocationEntity
        }

        private fun convertLocationAreaToLocationAreaEntity(locationArea: LocationArea): LocationAreaEntity {
            return LocationAreaEntity(name = locationArea.name, url = locationArea.url)
        }


    }


}

typealias Mapper<From, To> = (From) -> To