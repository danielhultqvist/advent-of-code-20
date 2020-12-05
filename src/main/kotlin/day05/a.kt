package day05a

import java.io.File

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()
    val max = lines
        .map { pos ->
            val row = binarySearch(pos.substring(0, 7), 'F', 0, 127)
            val col = binarySearch(pos.substring(7), 'L', 0, 7)
            row * 8 + col
        }
        .maxOrNull()!!
    println(max)
}

private fun binarySearch(value: String, lowerDelimiter: Char, from: Int, to: Int): Int {
    var start = from
    var stop = to
    for (element in value) {
        val mid = ((stop - start) / 2) + 1
        if (element == lowerDelimiter) {
            stop -= mid
        } else {
            start += mid
        }
    }
    return start
}
