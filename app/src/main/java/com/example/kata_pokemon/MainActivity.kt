package com.example.kata_pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kata_pokemon.ui.fragment.PokemonListFragment
import com.example.kata_pokemon.ui.theme.KatapokemonTheme
import com.example.kata_pokemon.utils.Route
import com.example.kata_pokemon.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
    var navController =  rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.List.route
    )
    {
        composable(
            route = Route.List.route
        )
        {
            goToPokemonList()
        }
    }
}

@Composable
fun goToPokemonList(){
    PokemonListFragment()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KatapokemonTheme {
        setupNavigation()
    }
}