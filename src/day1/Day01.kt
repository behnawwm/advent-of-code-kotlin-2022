package day1

import readInput

fun main() {
    val fileLines = readInput("day01", "day1")
    val calorieMap = mutableMapOf<Int, Int>()

    fun part1(): Int {
        return calorieMap.maxBy { it.value }.value
    }

    fun part2(): Int {
        return calorieMap.values.sortedDescending().take(3).sum()
    }


    var elfCounter = 0
    var foodSum = 0

    fileLines.forEach { foodCalorie ->
        if (foodCalorie.isBlank()) {
            calorieMap[elfCounter] = foodSum
            foodSum = 0
            elfCounter++
        } else {
            foodSum += foodCalorie.toInt()
        }
    }

    println(part1())
    println(part2())
}