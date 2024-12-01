import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val sortedFirst = input
            .map {
                it.split("   ").first()
                    .toString()
                    .toInt()
            }
            .sorted()
        val sortedLast = input
            .map {
                it.split("   ").last()
                    .toString()
                    .toInt()
            }
            .sorted()
        return sortedFirst
            .zip(sortedLast)
            .fold(0) { sum, pair ->
                sum + abs(pair.second - pair.first)
            }
    }

    fun part2(input: List<String>): Long {
        val firstList = input
            .map {
                it.split("   ").first()
                    .toString()
                    .toLong()
            }

        val secondList = input
            .map {
                it.split("   ").last()
                    .toString()
                    .toLong()
            }

        return firstList
            .fold(0) { sum, num ->
                sum + (num * secondList.count { it == num })
            }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
