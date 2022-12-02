package day2

import day2.Move.*
import day2.WinStatus.*
import readInput


fun main() {

//    val fileLines = readInput("day02-test", "day2")
    val fileLines = readInput("day02", "day2")
    var score = 0

    fun parseInput(text: String): Pair<String, String> {
        text.split(" ").apply {
            return Pair(get(0), get(1))
        }
    }

    fun part1(first: String, second: String) {
        val firstMove = first.toMove()
        val secondMove = second.toMove()

        score += secondMove.getValue()
        score +=
            if (secondMove == firstMove)
                3
            else if (secondMove < firstMove)
                0
            else
                6
    }

    fun part2(first: String, second: String) {
        val firstMove = first.toMove()
        val winStatus = second.toWinStatus()

        score += winStatus.getValue()

        val chosenMove = winStatus.getAccordingMove(firstMove)
        score += chosenMove.getValue()
    }

    fileLines.forEach { text ->
        val (first, second) = parseInput(text)

//        part1(first, second)
        part2(first, second)
    }
    println(score)
}

sealed interface Move : Comparable<Move> {
    fun getValue(): Int

    object Rock : Move {
        override fun getValue(): Int = 1

        override fun compareTo(other: Move): Int {
            return when (other) {
                Rock -> 0
                Paper -> -1
                Scissors -> 1
            }
        }
    }

    object Paper : Move {
        override fun getValue(): Int = 2

        override fun compareTo(other: Move): Int {
            return when (other) {
                Rock -> 1
                Paper -> 0
                Scissors -> -1
            }
        }
    }

    object Scissors : Move {
        override fun getValue(): Int = 3

        override fun compareTo(other: Move): Int {
            return when (other) {
                Rock -> -1
                Paper -> 1
                Scissors -> 0
            }
        }
    }

}

fun String.toMove(): Move {
    return when (this) {
        "X" -> Rock
        "A" -> Rock

        "Y" -> Paper
        "B" -> Paper

        "C" -> Scissors
        "Z" -> Scissors
        else -> Rock
    }
}

enum class WinStatus {
    SHOULD_WIN {
        override fun getValue(): Int = 6
    },
    SHOULD_LOSE {
        override fun getValue(): Int = 0
    },
    SHOULD_TIE {
        override fun getValue(): Int = 3
    };

    abstract fun getValue(): Int

    fun getAccordingMove(firstMove: Move): Move {
        return when (this) {
            SHOULD_WIN ->
                when (firstMove) {
                    Paper -> Scissors
                    Rock -> Paper
                    Scissors -> Rock
                }

            SHOULD_LOSE ->
                when (firstMove) {
                    Paper -> Rock
                    Rock -> Scissors
                    Scissors -> Paper
                }

            SHOULD_TIE ->
                when (firstMove) {
                    Paper -> Paper
                    Rock -> Rock
                    Scissors -> Scissors
                }
        }
    }
}

fun String.toWinStatus(): WinStatus {
    return when (this) {
        "X" -> SHOULD_LOSE
        "Y" -> SHOULD_TIE
        "Z" -> SHOULD_WIN

        else -> SHOULD_TIE
    }
}