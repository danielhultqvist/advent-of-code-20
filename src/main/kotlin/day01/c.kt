package day01

import java.io.File

fun main(args: Array<String>) {
    val numbers = File(args[0]).readLines().map { it.toInt() }
    println(solve(numbers, 2))
    println(solve(numbers, 3))
}

fun solve(numbers: List<Int>, tupleSize: Int) =
    numbers
        .combinations(tupleSize)
        .first { a -> a.sum() == 2020 }
        .reduce { a, b -> a * b }

fun <T> List<T>.combinations(tupleSize: Int): List<List<T>> {
    fun recurse(tupleSize: Int, currentDepth: Int, offset: Int): List<List<T>> {
        return (offset until this.size)
            .map { k ->
                if (currentDepth == tupleSize) {
                    listOf(listOf(this[k]))
                } else {
                    recurse(tupleSize, currentDepth + 1, k + 1)
                        .map { combo -> listOf(this[k]) + combo }
                }
            }
            .flatten()
    }
    return recurse(tupleSize, 1, 0)
}
