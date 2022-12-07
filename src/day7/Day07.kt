package day7

import readInput

fun main() {
    val fileLines = readInput("day07-test", "day7")
//    val fileLines = readInput("day07", "day7")

    fun updateStorage(currentDir: Dir, rootDir: Dir) {

    }

    fun addDirToCurrent(dirName: String, currentDir: Dir, rootDir : Dir) {
        if (currentDir.dirs.map { it.relativePath }.contains(dirName))
            return
        currentDir.dirs.add(
                Dir(dirName, currentDir)
        )
        updateStorage(currentDir,rootDir)

    }

    fun part1(fileLines: List<String>): Int {
        val rootDir = Dir("/", null)
        lateinit var currentDir: Dir

        fileLines.forEach {
            println("*****")
            when {
                it.startsWith('$') -> {
                    val commandLine = it.takeLast(it.length - 2).split(" ")
                    println(commandLine)
                    when (commandLine[0]) {
                        "cd" -> {
                            when (commandLine[1]) {
                                ".." -> {
                                    currentDir = currentDir.parentDir!!
                                }

                                "/" -> {
                                    currentDir = rootDir
                                }

                                else -> {
                                    currentDir.dirs.find {
                                        it.relativePath == commandLine[1]
                                    }.let {
                                        currentDir = it!!
                                    }
                                }
                            }
                        }

                        "ls" -> {

                        }
                    }

                }

                else -> {
                    val data = it.split(" ")
                    println(data)
                    when (data[0]) {
                        "dir" -> {
                            addDirToCurrent(data[1], currentDir)

//                            currentDir.dirs.add(
//                                    Dir(data[1], currentDir)
//                            )
                        }

                        else -> {
                            currentDir.files.add(
                                    File(data[1], data[0].toLong())
                            )
                        }
                    }
                }
            }
            println(rootDir)
        }

        print(rootDir)
        var count = 0
        return count
    }

    println(
            part1(fileLines)
    )
}

data class State(
        val storage: Dir,
        val pointer: Dir
)

data class Dir(
        val relativePath: String,
        val parentDir: Dir?,
        val dirs: MutableList<Dir> = mutableListOf(),
        val files: MutableList<File> = mutableListOf()
)

data class File(
        val name: String,
        val size: Long
)