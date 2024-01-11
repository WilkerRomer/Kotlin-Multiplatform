import data.Note
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


/*class ViewModel {

    /*{
    //State Flow
    private val _state: MutableStateFlow<Note> = MutableStateFlow(Note("title 1", "description 1", Note.Type.TEXT))
    val state: StateFlow<Note> = _state.asStateFlow()

    suspend fun update() {
        var count = 1
        while (true) {
            delay(2000)
            count++
            _state.value = Note("Title $count", "Description $count", Note.Type.TEXT)
        }
    }}...*/

    /*.{ //SharedFlow
    private val _state = MutableSharedFlow<Note>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val state = _state.asSharedFlow()


    suspend fun update() {
        var count = 1
        while (true) {
            delay(500)
            _state.emit(Note("Title $count", "Description $count", Note.Type.TEXT))
            println("Emitting Title$count")
            count++
        }
    }
    }...*/

    //Callback Flow
    fun update(callback: (Note) -> Unit) {
        var count = 1
        while (true) {
            Thread.sleep(500)
            callback(Note("Title $count", "Description $count", Note.Type.TEXT))
            println("Emitting Title$count")
            count++
        }
    }
}
//CallbackFlow
fun ViewModel.updateFlow(): Flow<Note> = callbackFlow {
    update { trySend(it) }
}

//Ejemplos flows
fun main(): Unit = runBlocking {

    /*val res = flow {
        emit(1)
        delay(300)
        emit(2)
        delay(300)
        emit(3)
        delay(300)
        emit(4)
    }.transform {
        if (it % 2 == 0 ) emit("Item $it")
    }

    launch {
        res.collect{
            println(it)
        }
}
//ejm 2
    val flow1 = flowOf(1, 2, 3, 4).onEach { delay(300) }
    val flow2 = flowOf("a", "b", "c").onEach { delay(500) }

    println(flow1.combine(flow2){ a, b -> "$a -> $b"}.toList())*/

// contextos y excepciones

    /* val flow = flow {
            emit(2)
        throw IllegalStateException("Exception message")
    }

    flow
        .flowOn(Dispatchers.IO)
        .catch { throwable -> println(throwable.message) }
        .collect{
        println(it)
    }*/

    /*{
    //State Flow
        val viewModel = ViewModel()
        launch {
            viewModel.update()
        }
        viewModel.state.collect(::println)
    }..*/

    /*{ //SharedFlow
        val viewModel = ViewModel()
        launch {
            viewModel.update()
        }
        delay(2100)
        viewModel.state.collect {
            delay(1000)
            println(it)
        }
    }..*/

    //CallbackFlow
    val viewModel = ViewModel()
    launch(Dispatchers.Default) {
        viewModel.updateFlow().collect {
            println(it)
        }
    }

}*/

//Test de otro modulo
//Clase sellada

sealed class CanWalk(val legs: Int){
    class Elephant(val name: String) : CanWalk(4)
    class Spider(val age: Int) : CanWalk(8)
}

sealed interface CanFly

object Swan: CanWalk(2), CanFly
object Fly: CanFly

fun test(canWalk: CanWalk): Int = when (canWalk) {
    is CanWalk.Elephant -> TODO()
    is CanWalk.Spider -> canWalk.age
    Swan -> TODO()
}

fun test2(canFly: CanFly): Int = when(canFly){
    Fly -> TODO()
    Swan -> TODO()
}

val elephant = CanWalk.Elephant("eduard")
