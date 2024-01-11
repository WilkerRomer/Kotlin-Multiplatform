package ui.screens.home
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.Note

@Composable
@Preview
fun Home(viewModel: HomeViewModel, onNoteClick: (noteId: Long) -> Unit ){

    MaterialTheme {
        Scaffold(
            topBar = {TopBar(onFilterClick = viewModel::onFilterClick)},
            floatingActionButton = {
                FloatingActionButton(onClick = { onNoteClick(Note.NEW_NOTE)}) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
                }
            }
        ) { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                if (viewModel.state.loading) {
                    CircularProgressIndicator()
                }

                viewModel.state.filteredNotes?.let { notes ->
                    NoteList(
                        notes = notes,
                        onNoteClick =  { onNoteClick(it.id)}
                    )
                }
            }
        }
    }
}


