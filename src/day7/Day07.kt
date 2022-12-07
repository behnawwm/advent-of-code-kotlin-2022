package day7

import readInput

fun main() {
    val fileLines = readInput("day07-test", "day7")
//    val fileLines = readInput("day07", "day7")


    fun updateDirsCurrentDir(newDirName: String, storage: Dir) {
//        if (dirName in currentDir.dirs.map { it.relativePath })
//            return
        val current = storage.currentDir
        current?.dirs?.removeIf {
            it.relativePath == current.relativePath && it.parentDir == current.parentDir
        }

        current?.dirs?.add(newDir)
    }

    fun updateFilesCurrentDir(newFile: File, storage: Dir) {
//        if (dirName in currentDir.dirs.map { it.relativePath })
//            return
        val current = storage.currentDir
        current?.files?.removeIf {
            it.name == current.relativePath
        }
        current?.files?.add(newFile)
    }

    fun part1(fileLines: List<String>): Int {
        val storage = Dir("/", null)

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
                                    storage.currentDir = storage.parentDir
                                }

                                "/" -> {
                                    storage.currentDir = storage.parentDir
                                }

                                else -> {
                                    storage.currentDir = storage.currentDir?.dirs?.find { it.relativePath == commandLine[1] }

//                                    currentDir.dirs.find {
//                                        it.relativePath == commandLine[1]
//                                    }.let {
//                                        currentDir = it!!
//                                    }
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
                            updateDirsCurrentDir(data[1],storage)

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
            println(storage)
        }

        print(storage)
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
        var currentDir: Dir? = null,
        val dirs: MutableList<Dir> = mutableListOf(),
        val files: MutableList<File> = mutableListOf()
)

data class File(
        val name: String,
        val size: Long
)