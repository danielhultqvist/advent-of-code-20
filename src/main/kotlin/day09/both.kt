package day09

import day01.combinations
import java.io.File

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines().map { it.toLong() }

    val preambleLength = 25
    val a = findWeakness(lines, preambleLength)
    val b = findContinuousSet(lines, a).sorted().let { it.first() + it.last() }

    println("A: $a")
    println("B: $b")
}

fun findWeakness(lines: List<Long>, preambleLength: Int): Long {
    for (position in preambleLength until lines.size) {
        val missing = lines
            .subList(position - preambleLength, position)
            .combinations(2)
            .filter { it[0] != it[1] && it.sum() == lines[position] }
            .none()

        if (missing) {
            return lines[position]
        }
    }
    throw IllegalStateException("Unable to find weakness")
}

fun findContinuousSet(lines: List<Long>, weakness: Long): List<Long> {
    // The ?.let { .. } is obscure, but just to test Kotlin features (dont use in production code)
    for (windowSize in 2 until lines.size) {
        lines
            .windowed(windowSize)
            .firstOrNull { it.sum() == weakness }
            ?.let { return it }
    }
    throw IllegalStateException("Unable to find continuous set")
}
