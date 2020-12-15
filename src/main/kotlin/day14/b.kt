package day14b

import java.io.File

data class Instruction(val memoryLocation: Long, val value: Long) {
    companion object {
        fun from(value: String): Instruction {
            val values = "mem\\[([0-9]+)\\] = ([0-9]+)".toRegex().find(value)!!.groupValues
            return Instruction(values[1].toLong(), values[2].toLong())
        }
    }
}

data class Mask(val mask: String) {

    fun apply(value: Long): List<Long> {
        val valueAsBinary = value.toString(2).padStart(36, '0')

        fun recurse(value: String, currentAddress: String): List<Long> {
            if (currentAddress.length == value.length) {
                return listOf(currentAddress.toLong(2))
            } else {
                val currentMaskBit = mask[currentAddress.length]
                return if (currentMaskBit == 'X') {
                    recurse(value, currentAddress + '0') + recurse(value, currentAddress + '1')
                } else {
                    if (currentMaskBit == '1' || value[currentAddress.length] == '1') {
                        recurse(value, currentAddress + '1')
                    } else {
                        recurse(value, currentAddress + '0')
                    }
                }
            }
        }
        return recurse(valueAsBinary, "")
    }
}

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()
    var mask = Mask(lines[0].replace("mask = ", ""))
    val memory = mutableMapOf<Long, Long>()
    lines.drop(1)
        .map {
            if (it.startsWith("mask")) {
                mask = Mask(it.replace("mask = ", ""))
            } else {
                val instruction = Instruction.from(it)
                val locations = mask.apply(instruction.memoryLocation)
                locations.forEach { i -> memory[i] = instruction.value }
            }
        }

    println(memory.values.sum())
}
