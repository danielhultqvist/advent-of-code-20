package day05a

import java.io.File

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()
    val seats = lines
        .map { it.replace("[BR]".toRegex(), "1").replace("[FL]".toRegex(), "0").toInt(2) }

    val a = seats.maxOrNull()
    val b = seats
        .sorted()
        .zipWithNext()
        .find { (first, second) -> first + 2 == second }!!
        .first + 1

    println(a)
    println(b)
}
