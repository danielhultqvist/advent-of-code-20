package day14

import java.io.File

data class Instruction(val memoryLocation: Long, val value: Long) {
    companion object {
        fun from(value: String): Instruction {
            val values = "mem\\[([0-9]+)\\] = ([0-9]+)".toRegex().find(value)!!.groupValues
            return Instruction(values[1].toLong(), values[2].toLong())
        }
    }
}

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()
    var negativeMask = lines[0].replace("mask = ", "").replace("X", "0").toLong(2)
    var positiveMask = lines[0].replace("mask = ", "").replace("X", "1").toLong(2)
    val memory = mutableMapOf<Long, Long>()

    lines.drop(1)
        .map {
            if (it.startsWith("mask")) {
                negativeMask = it.replace("mask = ", "").replace("X", "0").toLong(2)
                positiveMask = it.replace("mask = ", "").replace("X", "1").toLong(2)
            } else {
                val instruction = Instruction.from(it)
                val value = positiveMask.and(negativeMask.or(instruction.value))
                memory.put(instruction.memoryLocation, value)
            }
        }

    println(memory.values.sum())
}
