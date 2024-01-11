import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.App
import ui.screens.home.Home

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}
