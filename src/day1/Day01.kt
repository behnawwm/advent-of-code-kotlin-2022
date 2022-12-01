package day1

import readInput

fun main() {
    val fileLines = readInput("day01-test", "day1")
    val calorieMap = mutableMapOf<Int, Int>()

    fun part1(): Int {
        return calorieMap.maxBy { it.value }.value
    }

    fun part2(): Int {
        return calorieMap.values.sortedDescending().take(3).sum()
    }


    var elfCounter = 0
    var foodSum = 0

    fileLines.forEach { fileLine ->
        if (fileLine.isBlank()) {
            calorieMap[elfCounter] = foodSum
            foodSum = 0
            elfCounter++
        } else {
            foodSum += fileLine.toInt()
        }
    }

    println(part1())
    println(part2())

    // method 2 !
    val calorieList = mutableListOf<Int>()
    var prevIndex = 0
    fileLines.forEachIndexed { index, fileLine ->
        if (fileLine.isBlank()) {
            calorieList.add(
                fileLines.subList(prevIndex, index).map { it.toInt() }.sum()
            )
            prevIndex = index + 1
        }
        if (index == fileLines.lastIndex)
            calorieList.add(
                fileLines.subList(prevIndex, index + 1).map { it.toInt() }.sum()
            )
    }
    println(calorieList)
}