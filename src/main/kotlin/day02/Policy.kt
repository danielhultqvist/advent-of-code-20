package day02

interface Policy {
    fun fulfilled(value: String): Boolean
}

class RangePolicy(val min: Int, val max: Int, val character: Char): Policy {
    override fun fulfilled(value: String): Boolean {
        return value.count { it == character } in min..max
    }
    companion object {
        fun from(value: String): RangePolicy {
            val (min, max, character) = """(\d+)-(\d+) ([a-z])""".toRegex()
                .find(value)!!.destructured
            return RangePolicy(min.toInt(), max.toInt(), character[0])
        }
    }
}

class PositionPolicy(val first: Int, val second: Int, val character: Char): Policy {
    override fun fulfilled(value: String): Boolean {
        return (value.elementAt(first - 1) == character).xor(value.elementAt(second - 1) == character)
    }
    companion object {
        fun from(value: String): PositionPolicy {
            val (first, second, character) = """(\d+)-(\d+) ([a-z])""".toRegex()
                .find(value)!!.destructured
            return PositionPolicy(first.toInt(), second.toInt(), character[0])
        }
    }
}
