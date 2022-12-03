package day3

import readInput

fun main() {
//    val fileLines = readInput("day03-test", "day3")
    val fileLines = readInput("day03", "day3")


    fun part1(fileLines: List<String>): Int {
        var sum = 0
        fileLines.forEach { text ->
            val text1 = text.substring(0, text.length / 2)
            val text2 = text.substring(text.length / 2, text.length)

            val set1 = text1.toCharArray().toSet()
            val set2 = text2.toCharArray().toSet()

            val point = set1.intersect(set2).first().toPriorityPoint()
            sum += point
//            println("${set1.intersect(set2).first()} -> $point")
        }

        return sum
    }

    fun part2(fileLines: List<String>): Int {
        var sum = 0

        fileLines.forEach { text ->
            val chunkedList = fileLines.chunked(3)

            chunkedList.forEach { group ->
                val s1 = group[0].toCharArray().toSet()
                val s2 = group[1].toCharArray().toSet()
                val s3 = group[2].toCharArray().toSet()

                val commonChar = s1.intersect(s2).intersect(s3)
                sum += commonChar.first().toPriorityPoint()
            }
        }
        return sum
    }

//    val sum = part1(fileLines)
    val sum = part2(fileLines)

    println(sum)
}

const val lowercaseStart: Int = 'a'.code
const val uppercaseStart: Int = 'A'.code

private fun Char.toPriorityPoint(): Int {
    return if (isLowerCase()) {
        this.code - lowercaseStart + 1
    } else {
        this.code - uppercaseStart + 27
    }
}

