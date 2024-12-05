fun main() {
    fun part1(input: List<String>): Int {
        val word = "XMAS"
        val directions = listOf(
            Pair(0, 1),  // right
            Pair(1, 0),  // down
            Pair(1, 1),  // down-right
            Pair(1, -1), // down-left
            Pair(0, -1), // left
            Pair(-1, 0), // up
            Pair(-1, -1),// up-left
            Pair(-1, 1)  // up-right
        )

        fun isValid(x: Int, y: Int, dx: Int, dy: Int): Boolean {
            for (i in word.indices) {
                val nx = x + i * dx
                val ny = y + i * dy
                if (nx !in input.indices || ny !in input[0].indices || input[nx][ny] != word[i]) {
                    return false
                }
            }
            return true
        }

        var count = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                for ((dx, dy) in directions) {
                    if (isValid(i, j, dx, dy)) {
                        count++
                    }
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        return 0
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
