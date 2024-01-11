package ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.Filter
import data.Note
import data.remote.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(val scope: CoroutineScope) {

    /*private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()*/

    var state by mutableStateOf(UiState())
        private set

    init {
        loadNotes()
    }

    private fun loadNotes() {
        scope.launch{
            state = UiState(loading = true)
            val response = NoteRepository.getAll()
            state = UiState(notes = response)
        }
    }

    fun onFilterClick(filter: Filter) {
        state = state.copy(filter = filter)
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean = false,
        val filter: Filter = Filter.All
    ) {
        val filteredNotes: List<Note>?
            get() = when(filter){
            Filter.All -> notes
            is Filter.ByType -> notes?.filter { it.type == filter.type }
        }
    }
}