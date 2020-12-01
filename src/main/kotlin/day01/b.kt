package day01
import java.io.File

fun main(args: Array<String>) {
    val wantedSum = 2020
    val numbers = File(args[0]).readLines().map { it.toInt() }

    for(i in numbers.indices) {
        for (j in (i + 1) until numbers.size) {
            for (k in (j + 1) until numbers.size) {
                if (numbers[i] + numbers[j] + numbers[k] == wantedSum) {
                    val multiplied = numbers[i] * numbers[j] * numbers[k]
                    println("Wanted combination: ${numbers[i]}, ${numbers[j]}, ${numbers[k]} gives $multiplied")
                }
            }
        }
    }
}
