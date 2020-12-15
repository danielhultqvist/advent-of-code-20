package day11b

import java.io.File
import java.lang.IllegalStateException
import java.lang.Thread.sleep
import kotlin.math.max

data class Matrix(val value: List<String>) {
    fun valueOrDefault(row: Int, col: Int, defaultValue: Char): Char {
        return if (isOutside(row, col)) {
            defaultValue;
        } else {
            try {
                value[row][col]
            } catch (e: Exception) {
                throw IllegalStateException(e)
            }
        }
    }

    fun isOutside(row: Int, col: Int): Boolean {
        return value.size <= row || row < 0 || value[0].length <= col || col < 0
    }

    fun rows(): Int {
        return value.size
    }

    fun cols(): Int {
        return value[0].length
    }

    override fun toString(): String {
        return value.joinToString(separator = "\n")
    }
}

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()
    val matrix = Matrix(lines)

    var previous = matrix
    var current = matrix
    do {
        previous = current
        current = calculate(current)
    } while (previous != current)

    println(current)
    println("\n\n\n")
    println(current.value.joinToString { it }.count { it == '#' })
}

fun calculate(input: Matrix): Matrix {
    var next = ""
    for (r in 0 until input.rows()) {
        for (c in 0 until input.cols()) {
            var surrounding = 0
            for (i in -1..1) {
                for (j in -1..1) {
                    if (i == 0 && j == 0) {
                        continue
                    }

                    var notFound = true
                    var steps = 1
                    while (notFound) {
                        val row = r + i*steps
                        val col = c + j*steps
                        if (input.isOutside(row, col) || input.valueOrDefault(row, col, '.') == 'L') {
                            notFound = false
                        } else if (input.valueOrDefault(row, col, '.') == '#') {
                            surrounding++
                            notFound = false
                        }
                        steps++
                    }
                }
            }

            val currentValue = input.valueOrDefault(r, c, '.')
            if (currentValue == '#' && surrounding >= 5) {
                next = "${next}L"
            } else if (currentValue == 'L' && surrounding == 0) {
                next = "${next}#"
            } else {
                next = "${next}${currentValue}"
            }
        }
    }
    return Matrix(next.chunked(input.cols()))
}
