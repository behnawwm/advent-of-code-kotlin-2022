package day4

import readInput

fun main() {
    val fileLines = readInput("day04", "day4")

    fun String.parseInput(): Pair<IntRange, IntRange> {
        val (s1, e1) = substringBefore(",").split("-").map { it.toInt() }
        val (s2, e2) = substringAfter(",").split("-").map { it.toInt() }
        val range1 = s1..e1
        val range2 = s2..e2

        return range1 to range2
    }

    fun part1(fileLines: List<String>): Int {
        var sum = 0
        fileLines.forEach {
            val (range1, range2) = it.parseInput()
            if ((range1.first in range2 && range1.last in range2) || (range2.first in range1 && range2.last in range1))
                sum += 1
        }
        return sum
    }

    fun part2(fileLines: List<String>): Int {
        var sum = 0
        fileLines.forEach {
            val (range1, range2) = it.parseInput()
            if (range1.first in range2 || range1.last in range2 || range2.first in range1 || range2.last in range1)
                sum += 1
        }
        return sum
    }

//    println(part1(fileLines))
    println(part2(fileLines))
}