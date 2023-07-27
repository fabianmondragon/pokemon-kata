package com.example.kata_pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kata_pokemon.ui.fragment.PokemonDetailFragment
import com.example.kata_pokemon.ui.fragment.PokemonListFragment
import com.example.kata_pokemon.ui.fragment.goToDetailOfPokemon
import com.example.kata_pokemon.ui.theme.KatapokemonTheme
import com.example.kata_pokemon.utils.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KatapokemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setupNavigation()
                }
            }
        }
    }
}

@Composable
fun setupNavigation() {
    var navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.List.route
    )
    {
        composable(
            route = Route.List.route
        )
        {
            goToPokemonList(navController)
        }
        composable(
            route = "${Route.Detail.route}/{parameter}/{pokemonId}",
            arguments = listOf(
                navArgument("parameter") { type = NavType.StringType },
                navArgument("pokemonId") { type = NavType.StringType }

            )
        )
        { navBackStackEntry ->
            val parameter = navBackStackEntry.arguments?.getString("parameter")
            val pokemonId = navBackStackEntry.arguments?.getString("pokemonId")
            parameter?.let { parameter ->
                pokemonId.let {
                    PokemonDetailFragment(nameOfPokemon = parameter, pokemonId = it!!)
                }

            }


        }
    }
}

@Composable
fun goToPokemonList(navController: NavController) {
    PokemonListFragment(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KatapokemonTheme {
        setupNavigation()
    }
}