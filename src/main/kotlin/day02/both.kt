package day02

import java.io.File

data class PasswordWithPolicy(val password: String, val policy: Policy)

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines()

    val a = lines
        .map { PasswordWithPolicy(passwordFrom(it), RangePolicy.from(it)) }
        .count { (password, policy) -> policy.fulfilled(password) }

    val b = lines
        .map { PasswordWithPolicy(passwordFrom(it), PositionPolicy.from(it)) }
        .count { (password, policy) -> policy.fulfilled(password) }

    println("A: $a")
    println("B: $b")
}

fun passwordFrom(value: String): String {
    return value.substring(value.lastIndexOf(" ") + 1)
}

