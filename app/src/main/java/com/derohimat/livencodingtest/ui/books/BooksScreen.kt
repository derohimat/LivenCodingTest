package com.derohimat.livencodingtest.ui.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import com.derohimat.livencodingtest.domain.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(viewModel: BooksViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Books (Compose)") }) }) { paddingValues ->
        Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is BooksUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is BooksUiState.Success -> {
                    BooksList(books = state.books)
                }
                is BooksUiState.Error -> {
                    Text(text = state.message, color = MaterialTheme.colorScheme.error)
                }
                is BooksUiState.Empty -> {
                    Text("No books found")
                }
            }
        }
    }
}

@Composable
fun BooksList(books: List<Book>) {
    LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
    ) { items(books) { book -> BookItem(book = book) } }
}

@Composable
fun BookItem(book: Book) {
    Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Image(
                    painter = rememberAsyncImagePainter(book.imageUrl),
                    contentDescription = book.title,
                    modifier = Modifier.width(80.dp).height(120.dp),
                    contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                        text = book.author,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                        text = book.description,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
