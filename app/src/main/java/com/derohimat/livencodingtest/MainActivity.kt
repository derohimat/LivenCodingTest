package com.derohimat.livencodingtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.derohimat.livencodingtest.ui.book.BooksScreen
import com.derohimat.livencodingtest.ui.book.BooksViewModel
import com.derohimat.livencodingtest.ui.theme.LivenCodingTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LivenCodingTestTheme() {
                BooksApp()
            }
        }
    }
}

@Composable
fun BooksApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "books"
    ) {
        composable("books") {
            val booksViewModel: BooksViewModel = hiltViewModel()
            BooksScreen(
                viewModel = booksViewModel,
                onNavigateToDetail = { bookId ->
                }
            )
        }
    }
}