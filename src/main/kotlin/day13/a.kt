package day13

import java.io.File

data class BusDeparture(val busId: Long, val departureTime: Long)

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()
    val earliestDeparture = lines[0].toLong()
    val schedule = lines[1].split(",").filter { it != "x" }.map { it.toLong() }

    val earliestBus = schedule
        .map { BusDeparture(it, (earliestDeparture / it) * it + it) }
        .minByOrNull { it.departureTime }!!
    val waitTime = earliestBus.departureTime - earliestDeparture

    println("Earliest bus: ${earliestBus.busId}, departing at ${earliestBus.departureTime}. Wait time ${waitTime}")
    println("A: ${earliestBus.busId * waitTime}")
}
