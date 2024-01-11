package ui.screens.home
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import data.Filter
import data.Note.*

@Composable
fun TopBar(onFilterClick: (Filter) -> Unit) {
    TopAppBar(
        title = { Text("My Notes") },
        actions = {
            FiltersActions(onFilterClick)
        }
    )
}

@Composable
private fun FiltersActions(onFilterClick: (Filter) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = {expanded = true}) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter"
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listOf(
                Filter.All to "All",
                Filter.ByType(Type.TEXT) to "Text",
                Filter.ByType(Type.AUDIO) to "Audio"
            ).forEach { (filter, label) ->
                DropdownMenuItem(onClick = {
                    onFilterClick(filter)
                    expanded = false
                }) {
                    Text(label)
                }
            }
        }
    }
}