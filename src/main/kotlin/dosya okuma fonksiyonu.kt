import java.io.File

val inputLinesList = mutableListOf<String>()
val transitionLinesList = mutableListOf<MutableList<String>>()

fun readAllFiles(): Boolean{

    try {
        val inputDosya = File("src/input.txt")
        val bufferedReader = inputDosya.bufferedReader()

        bufferedReader.useLines { lines ->
            lines.forEach { line ->
                inputLinesList.add(line)
            }
        }
    } catch (e: Exception) {
        println("dosya okuma hatası: input file ${e.message}")
        return false
    }

    try {
        val inputDosya = File("src/transitionTable.txt")
        val bufferedReader = inputDosya.bufferedReader()

        bufferedReader.useLines { lines ->
            lines.forEach { line ->
                transitionLinesList.add(getValuesFromTableLines(line))
            }
        }
    } catch (e: Exception) {
        println("dosya okuma hatası: transitionTable ${e.message}")
        return false
    }
return true
}