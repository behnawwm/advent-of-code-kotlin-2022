package day6

import readInput

fun main() {
    val fileLines = readInput("day06", "day6")
//    val fileLines = readInput("day06", "day6")

    val signals = mutableListOf<Char>()

    fileLines.forEach {
        println(getFirstSignal(it))
    }

}

fun getFirstSignal(it: String): Int {
    var start = 0
    while (start + 14 < it.length) {
        val subString = it.substring(start, start + 14)

        if (subString.toCharArray().distinct().size == subString.length) {
            println(subString)
            return start + 14
        }
        start += 1
    }
    return 0
}
