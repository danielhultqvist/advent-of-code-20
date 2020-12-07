package day07

import java.io.File

fun traverseContains(bagsByBag: Map<String, Set<Pair<Int, String>>>, currentBag: String): Boolean {
    return if (currentBag == "shiny gold") {
        true
    } else {
        bagsByBag[currentBag]?.any { it -> traverseContains(bagsByBag, it.second) } ?: false
    }
}

// Look at that signature ma'!
fun traverseCount(
    bagsByBag: Map<String, Set<Pair<Int, String>>>,
    currentBag: Pair<Int, String>
): Int {
    val bags = bagsByBag[currentBag.second]!!
    return if (bags.isEmpty()) {
        0
    } else {
        bags.map { it.first * (1 + traverseCount(bagsByBag, it)) }.sum()
    }
}

fun main(args: Array<String>) {
    val bagsByBag = File(args[0]).readLines().map { bagToContainedBags(it) }.toMap()

    val a = bagsByBag
        .filterNot { it.key == "shiny gold" }
        .keys
        .filter { bag -> traverseContains(bagsByBag, bag) }
        .count()
    val b = traverseCount(bagsByBag, Pair(1, "shiny gold"))

    println(a)
    println(b)
}

private fun bagToContainedBags(rule: String): Pair<String, Set<Pair<Int, String>>> {
    val bag = "^([a-z]+ [a-z]+)".toRegex().find(rule)!!.value
    val containedBags =
        "(\\d+) ([a-z]+ [a-z]+)".toRegex()
            .findAll(rule)
            .map { match -> match.groupValues[1].toInt() to match.groupValues[2] }
            .toSet()

    return bag to containedBags
}
