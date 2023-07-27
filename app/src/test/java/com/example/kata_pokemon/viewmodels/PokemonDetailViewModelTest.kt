package com.example.kata_pokemon.viewmodels

import com.example.domain.models.*
import com.example.domain.usecases.GetDetailPokemonUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.createTestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
internal class PokemonDetailViewModelTest {

    @Mock
    lateinit var getDetailPokemonUseCase: GetDetailPokemonUseCase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = createTestCoroutineScope(testDispatcher)

    private lateinit var viewModel: PokemonDetailViewModel

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = PokemonDetailViewModel(getDetailPokemonUseCase)
    }

    @Test
    fun `test getDetailOfPokemon when success response`() = testScope.runBlockingTest {
        // Given
        val pokemonId = "1"
        val pokemonDetailEntity = createDummyPokemonDetailEntity()

        `when`(getDetailPokemonUseCase.getDetailOfPokemon(pokemonId)).thenReturn(
            flowOf(
                PokemonResponse.Success(pokemonDetailEntity)
            )
        )

        // When
        viewModel.getDetailOfPokemon(pokemonId)


        val actualDetailPokemon = viewModel.detailPokemonUi.value
        // Then
        advanceUntilIdle()
        assertEquals(pokemonDetailEntity, actualDetailPokemon)
    }


    private fun createDummyPokemonDetailEntity(): PokemonEvolutionAndDetailEntity {
        return PokemonEvolutionAndDetailEntity(
            detailPokemonEntity = DetailPokemonResponseEntity(
                name = "Pikachu",
                id = "1",
                moves = listOf(MoveEntity(MoveDetailEntity("asdf", url = "url"))),
                sprites = SpritesEntity(
                    other = OtherEntity(
                        dreamWorld = DreamWorldEntity("https://example.com/pikachu.png")
                    )
                )
            ),
            locationResponseEntity = emptyList(),
            evolutionChainEntity = EvolutionChainEntity(
                chain = ChainEntity(
                    evolvesTo = listOf(
                        EvolvesToEntity(
                            species = SpeciesEntity("Raichu"),
                            evolvesTo = listOf()
                        )
                    )
                )
            )
        )
    }
}
