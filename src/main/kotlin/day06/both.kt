package day06

import java.io.File

fun main(args: Array<String>) {
    val groups = File(args[0]).readText().split("\n\n").map { it.trim() }

    val a = groups.map { it.filter { c -> c.isLetter() }.toSet().count() }.sum()

    val b = groups.map {
        it.split("\n")
            .fold("abcdefghijklmnopqrstuvwxyz") { g, current ->
                current.filter { c -> g.contains(c) }
            }
            .count()
    }.sum()

    println(a)
    println(b)
}
