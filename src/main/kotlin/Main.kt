import kotlinx.coroutines.*
import kotlinx.coroutines.swing.Swing


fun main() {

    val job = Job()
    val scope = CoroutineScope(Dispatchers.Swing + job)

    printlnThreadName()

    scope.launch {
        for (index in 0 until 100) {
            insertToFakeDB("Ala")
        }
    }

    scope.launch {
        for (index in 0 until 50) {
            getDataFromNetwork()
        }
    }

    Thread.sleep(500)
    println("Dalsza kontynuacja programu")
    printlnThreadName()

    println("Przerwanie działania wszystkich wątków")
    job.cancel()

    Thread.sleep(5000)
    println("Koniec programu")
}

suspend fun insertToFakeDB(name: String) {
    delay(200)
    printlnThreadName()
    println("Insert to db: $name")
}

suspend fun getDataFromNetwork() {
    delay(100)
    printlnThreadName()
    println("Downloading data from network...")
}

fun printlnThreadName() = println("Thread name: ${Thread.currentThread()}")