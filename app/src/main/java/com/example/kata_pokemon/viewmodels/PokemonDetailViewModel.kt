package com.example.kata_pokemon.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.models.*
import com.example.domain.usecases.GetDetailPokemonUseCase
import com.example.kata_pokemon.ui.models.DetailPokemonUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getDetailPokemonUseCase: GetDetailPokemonUseCase
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _detailPokemonUi: MutableStateFlow<PokemonEvolutionAndDetailEntity> =
        MutableStateFlow(PokemonEvolutionAndDetailEntity())
    val detailPokemonUi = _detailPokemonUi.asStateFlow()

    fun getDetailOfPokemon(pokemonId: String) {
        coroutineScope.launch {
            getDetailPokemonUseCase.getDetailOfPokemon(pokemonId).collect { response ->
                when (response) {
                    is PokemonResponse.Success -> {
                        _detailPokemonUi.value = response.data
                    }
                    else -> {
                    }
                }
            }
        }

    }

    fun organizeData(respond: PokemonEvolutionAndDetailEntity) =

        respond.detailPokemonEntity?.sprites?.other?.dreamWorld?.let {
            it.frontDefault?.let { it1 ->
                respond.evolutionChainEntity?.chain?.let { it2 -> getEvolutions(it2.evolvesTo) }
                    ?.let { it3 ->
                        DetailPokemonUi(
                            url = it1,
                            types = getTypes(respond.detailPokemonEntity!!.types),
                            abilities = getAbilities(respond.detailPokemonEntity!!.abilities),
                            moves = getMovesPokemon(respond.detailPokemonEntity!!.moves),
                            name = respond.detailPokemonEntity!!.name,
                            id = respond.detailPokemonEntity!!.id,
                            location = getLocation(respond.locationResponseEntity),
                            evolution = it3
                        )
                    }
            }
        }

    private fun getTypes(types: List<TypeEntity>): String {
        var resultTypes = ""
        for (element in types) {
            resultTypes += "${element.type.name}, "
        }
        return resultTypes.dropLast(2)
    }

    private fun getAbilities(abilities: List<AbilityEntity>): String {
        var resultAbilities = ""
        for (element in abilities) {
            resultAbilities += "${element.ability.name}, "
        }
        return resultAbilities.dropLast(2)
    }

    private fun getMovesPokemon(moves: List<MoveEntity>): String {
        var resultMoves = ""
        for (element in moves) {
            resultMoves += "${element.move.name}, "
        }
        return resultMoves.dropLast(2)
    }

    private fun getLocation(list: List<LocationEntity>): String {
        var resultLocations = ""
        list as List<LocationAreaEntity>
        list.forEach {
            resultLocations += "${it.name}, "
        }

        return resultLocations.dropLast(2)
    }

    private fun getEvolutions(listEvolvesTo: List<EvolvesToEntity>): String {
        var resultEvolves = ""
        for (element in listEvolvesTo) {
            resultEvolves += "${element.species.name}, "
            element.evolvesTo?.forEach {
                resultEvolves += "${it.species.name}, "
            }
        }
        return resultEvolves.dropLast(2)
    }


}