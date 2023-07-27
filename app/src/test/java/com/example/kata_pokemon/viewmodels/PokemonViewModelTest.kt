package com.example.kata_pokemon.viewmodels

import com.example.domain.models.PokemonItemEntity
import com.example.domain.models.PokemonResponse
import com.example.domain.usecases.PokemonListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


internal class PokemonViewModelTest {
    private lateinit var pokemonViewModel: PokemonViewModel

    @Mock
    private lateinit var pokemonListUseCase: PokemonListUseCase
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = createTestCoroutineScope(testDispatcher)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        pokemonViewModel = PokemonViewModel(pokemonListUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getPokemons when success response`() = testScope.runBlockingTest {
        // Given
        val pokemonItemList = listOf(
            PokemonItemEntity(
                name = "Pikachu",
                id = "1",
                url = "pikachuUrl",
                image = "pikachuImg"
            ),
            PokemonItemEntity(
                name = "Charmander",
                id = "2",
                url = "CharmanderUrl",
                image = "pikachuImg"
            ),
            PokemonItemEntity(
                name = "Bulbasaur",
                id = "3",
                url = "BulbasurURl",
                image = "pikachuImg"
            )
        )

        val successResponse = PokemonResponse.Success(pokemonItemList)
        `when`(pokemonListUseCase.getPokemonList()).thenReturn(flowOf(successResponse))

        pokemonViewModel.getPokemons()

        advanceUntilIdle()
        assertEquals(pokemonItemList, pokemonViewModel.listState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getPokemons when different list fail response`() = testScope.runBlockingTest {

        // Given
        val pokemonItemList = listOf(
            PokemonItemEntity(
                name = "Pikachu",
                id = "1",
                url = "pikachuUrl",
                image = "pikachuImg"
            ),
            PokemonItemEntity(
                name = "Charmander",
                id = "2",
                url = "CharmanderUrl",
                image = "pikachuImg"
            ),
            PokemonItemEntity(
                name = "Bulbasaur",
                id = "3",
                url = "BulbasurURl",
                image = "pikachuImg"
            )
        )
        val pokemonItemList2 = listOf(
            PokemonItemEntity(
                name = "Pikachu",
                id = "1",
                url = "pikachuUrl",
                image = "pikachuImg"
            )
        )
        // When
        val successResponse = PokemonResponse.Success(pokemonItemList)
        `when`(pokemonListUseCase.getPokemonList()).thenReturn(
            flowOf(
                successResponse
            )
        )

        pokemonViewModel.getPokemons()
        advanceUntilIdle()

        // Then
        assertFalse(pokemonItemList2 == pokemonViewModel.listState.value)
        assertTrue(pokemonItemList == pokemonViewModel.listState.value)
    }


    private fun <T> flowOf(item: T): Flow<T> = MutableStateFlow(item)
}