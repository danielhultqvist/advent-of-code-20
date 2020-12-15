package day13b

import java.io.File

data class TimeAndStep(val t: Long, val step: Long)

/**
 * General idea
 * Find t such that
 * (t+0) % X1 == 0
 * (t+1) % X2 == 0
 * ...
 * (t+N) % Xx == 0
 *
 * Iterate looping through t, with increasing step size everytime a match has been
 */
fun main(args: Array<String>) {
    File(args[0])
        .readLines()[1]
        .split(",")
        .withIndex()
        .filter { (_, value) -> value != "x" }
        .fold(TimeAndStep(0, 1)) { acc, bus ->
            val busInterval = bus.value.toLong()
            var nextT = acc.t
            while (((nextT + bus.index) % busInterval) != 0L) {
                nextT += acc.step
            }
            TimeAndStep(nextT, acc.step * busInterval)
        }
        .let { println(it.t) }




    // Alternativ 2, more readable
    val buses = File(args[0]).readLines()[1].split(",")

    var stepSize = buses[0].toLong()
    var t = stepSize
    buses.withIndex()
        .drop(1)
        .filter { (_, value) -> value != "x" }
        .forEach { (offset, busId) ->
            while (((t + offset) % busId.toLong()) != 0L) {
                t += stepSize
            }
            stepSize *= busId.toLong()
        }
    println(t)
}
