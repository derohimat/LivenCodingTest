package com.derohimat.livencodingtest.ui.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.derohimat.livencodingtest.ui.theme.LivenCodingTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LivenCodingTestTheme { BooksScreen() } }
    }
}
