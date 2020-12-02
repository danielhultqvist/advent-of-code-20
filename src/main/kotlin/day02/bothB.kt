package day02

import java.io.File

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()

    val a =
        lines
            .map { """(\d+)-(\d+) ([a-z]): ([a-z]+)""".toRegex().find(it)!!.destructured }
            .count { (min, max, character, password) ->
                password.count { it == character[0] } in min.toInt()..max.toInt()
            }
    val b =
        lines
            .map { """(\d+)-(\d+) ([a-z]): ([a-z]+)""".toRegex().find(it)!!.destructured }
            .count { (first, second, character, password) ->
                (password[first.toInt() - 1] == character[0])
                    .xor((password[second.toInt() - 1] == character[0]))
            }

    println("A: $a")
    println("B: $b")
}

