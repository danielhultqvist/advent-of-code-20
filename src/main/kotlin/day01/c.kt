package day01

import java.io.File

fun main(args: Array<String>) {
    val numbers = File(args[0]).readLines().map { it.toInt() }
    println(solve(numbers, 2))
    println(solve(numbers, 3))
}

fun solve(numbers: List<Int>, tuples: Int) =
    numbers
        .combinations(tuples)
        .filter { a -> a.sum() == 2020 }
        .first()
        .reduce { a, b -> a * b }

fun List<Int>.combinations(count: Int): Sequence<List<Int>> {
    fun recurse(
        tupleSize: Int,
        currentDepth: Int,
        offset: Int
    ): List<List<Int>> {
        val combinations = mutableListOf<List<Int>>()
        for (k in offset until this.size) {
            if ((currentDepth + 1) == tupleSize) {
                combinations.add(listOf(this[k]))
            } else {
                recurse(tupleSize, currentDepth + 1, k + 1)
                    .forEach {combos -> combinations.add(listOf(this[k]) + combos)}
            }
        }
        return combinations;
    }

    val result = recurse(count, 0, 0)

    return result.asSequence()
}
