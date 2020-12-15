package day13b

import java.io.File

data class BusDeparture(val busId: Long, val offset: Long)

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()

    val schedule = lines[1].split(",").mapIndexedNotNull {i, busId ->
        if (busId == "x") {
            null
        } else {
            BusDeparture(busId.toLong(), i.toLong())
        }
    }

    var stepSize = schedule[0].busId
    var t = 0L

    schedule.drop(1).forEach { (busId, offset) ->
        while (((t + offset) % busId) != 0L) {
            t += stepSize
        }
        stepSize *= busId
    }

    println(t)
}
