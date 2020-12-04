package day04a

import java.io.File

fun main(args: Array<String>) {
    val lines = File(args[0]).readText().split("\n\n")
    val count = lines
        .map { it.replace("\n", " ").split(" ").filter { i -> i.isNotBlank() }.keys() }
        .count { it.containsAllRequiredFields() }

    println("A: $count")
}

fun List<String>.keys(): List<String> {
    return this.map { it.substring(0, it.indexOf(":")) }
}

fun List<String>.containsAllRequiredFields(): Boolean {
    return this.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))
}
