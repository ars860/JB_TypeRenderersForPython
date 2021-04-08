import kotlinx.coroutines.*

fun String.runCommand(): Process {
    return ProcessBuilder(*split(" ").toTypedArray())
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()
}

fun main(args: Array<String>) = runBlocking {
    if (args.size != 1) {
        println("Expected <Path to python executable> as first arg")
        return@runBlocking
    }

    val pathToPython = args[0]
    val process = "$pathToPython -m timeit -r 10".runCommand()

    var cnt = 0
    while (process.isAlive) {
        println(cnt++)
        delay(1000)
    }
}