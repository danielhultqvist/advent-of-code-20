package day04b

import java.io.File

fun main(args: Array<String>) {
    val lines = File(args[0]).readText().split("\n\n")
    val count = lines
        .map { it.replace("\n", " ").split(" ").filter { i -> i.isNotBlank() }.asMap() }
        .count { it.containsAllRequiredFields() }

    println("B: $count")
}

fun List<String>.asMap(): Map<String, String> {
    return this
        .map { it.substring(0, it.indexOf(":")) to it.substring(it.indexOf(":") + 1) }
        .toMap()
}

fun Map<String, String>.containsAllRequiredFields(): Boolean {
    return this["byr"]?.matches("[0-9]{4}".toRegex()) ?: false && this["byr"]!!.toInt() in 1920..2002
            && this["iyr"]?.matches("[0-9]{4}".toRegex()) ?: false && this["iyr"]!!.toInt() in 2010..2020
            && this["eyr"]?.matches("[0-9]{4}".toRegex()) ?: false && this["eyr"]!!.toInt() in 2020..2030
            && this["hcl"]?.matches("#[a-f0-9]{6}".toRegex()) ?: false
            && this["ecl"]?.matches("amb|blu|brn|gry|grn|hzl|oth".toRegex()) ?: false
            && this["pid"]?.matches("[0-9]{9}".toRegex()) ?: false
            && this["hgt"]?.matches("[0-9]+(cm|in)".toRegex()) ?: false && validHeight(this["hgt"]!!)
}

fun validHeight(value: String): Boolean {
    return if (value.contains("cm")) {
        value.replace("cm", "").toInt() in 150..193
    } else {
        value.replace("in", "").toInt() in 59..76
    }
}
