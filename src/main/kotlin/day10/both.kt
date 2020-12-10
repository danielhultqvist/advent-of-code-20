package day10

import java.io.File
import kotlin.math.max

data class Adapter(val joltage: Int, val combinationsToReachMe: Long)

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines().map { it.toInt() }

    val jolts = lines
        .sorted()
        .zipWithNext()
        .map { it.second - it.first }
        .groupBy { it }
    val oneJoltDiff = jolts[1]!!.size + 1 // First adapter is always one diff
    val threeJoltDiff = jolts[3]!!.size + 1 // Last adapter is always three diff against device
    println("A: ${oneJoltDiff * threeJoltDiff}")

    val b = numberOfCombinations(lines, 3)
    println("B: $b")
}

private fun numberOfCombinations(lines: List<Int>, maxDistance: Int): Long {
    /* Algorithm:
       For each nodes that reaches me, calculate the number of paths it has to the start
       Divide and conquer by storing the result for each adapter,
       going from first to last in order to reuse calculated result.
     */
    return lines
        .sorted()
        .foldIndexed(listOf(Adapter(0, 1L))) { idx, acc, jolt ->
            val before = acc
                .subList(max(0, idx - maxDistance), idx + 1)
                .filter { jolt - it.joltage <= maxDistance }

            val value = before.map { it.combinationsToReachMe }.sum()
            acc + listOf(Adapter(jolt, value))
        }
        .last()
        .combinationsToReachMe
}
