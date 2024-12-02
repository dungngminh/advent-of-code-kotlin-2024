import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map { e ->
                val num = e.split(" ").map { it.toInt() }
                if (isAllIncreasingOrDecreasing(num) && isAllElementLevelDiffIn1To3(num)) 1 else 0
            }.sum()
    }

    fun part2(input: List<String>): Int {
        return input
            .map { e ->
                val num = e.split(" ").map { it.toInt() }
                if (isAllElementLevelDiffIn1To3WithDampener(num)) 1 else 0
            }.sum()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    part1(testInput).println()


    check(part1(testInput) == 2)
    val input = readInput("Day02")
    println(part1(input))
    part2(testInput).println()
    println(part2(input))

}

fun isAllElementLevelDiffIn1To3WithDampener(num: List<Int>): Boolean {
    for (i in num.indices) {
        val newList = num.toMutableList().apply { removeAt(i) }
        if (isAllIncreasingOrDecreasing(newList) && isAllElementLevelDiffIn1To3(newList)) return true
    }
    return false
}

fun isAllElementLevelDiffIn1To3(num: List<Int>): Boolean {
    return num
        .zipWithNext { a, b -> abs(a - b) }
        .all { it in 1..3 }
}

fun isAllIncreasingOrDecreasing(num: List<Int>): Boolean {
    return num.zipWithNext().all { (a, b) -> a < b } || num.zipWithNext()
        .all { (a, b) -> a > b }
}