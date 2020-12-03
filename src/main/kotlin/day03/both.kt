package day03

import java.io.File

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()

    val a = findTreeHits(lines, 1, 1).toLong()
    val b = findTreeHits(lines, 3, 1)
    val c = findTreeHits(lines, 5, 1)
    val d = findTreeHits(lines, 7, 1)
    val e = findTreeHits(lines, 1, 2)

    println("A: $b")
    println("B: ${a * b * c * d * e}")
}

private fun findTreeHits(lines: List<String>, right: Int, down: Int): Int {
    val width = lines[0].length
    return (0 until lines.size / down).count { lines[it * down][(it * right) % width] == '#' }
}
