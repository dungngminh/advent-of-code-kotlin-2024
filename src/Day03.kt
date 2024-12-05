fun main() {
    fun part1(input: List<String>): Int {
        val regex = Regex("""mul\((\d+),(\d+)\)""")
        var sum = 0
        for (line in input) {
            regex.findAll(line).forEach { matchResult ->
                val (x, y) = matchResult.destructured
                sum += x.toInt() * y.toInt()
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val mulRegex = Regex("""mul\((\d+),(\d+)\)""")
        val doRegex = Regex("""do\(\)""")
        val dontRegex = Regex("""don't\(\)""")
        var sum = 0
        var mulEnabled = true

        for (line in input) {
            var currentIndex = 0
            while (currentIndex < line.length) {
                val mulMatch = mulRegex.find(line, currentIndex)
                val doMatch = doRegex.find(line, currentIndex)
                val dontMatch = dontRegex.find(line, currentIndex)

                val nextMatch =
                    listOfNotNull(mulMatch, doMatch, dontMatch)
                        .minByOrNull { it.range.first }

                if (nextMatch != null) {
                    currentIndex = nextMatch.range.last + 1
                    when (nextMatch.value) {
                        doMatch?.value -> mulEnabled = true
                        dontMatch?.value -> mulEnabled = false
                        mulMatch?.value -> {
                            if (mulEnabled) {
                                val (x, y) = mulMatch.destructured
                                sum += x.toInt() * y.toInt()
                            }
                        }
                    }
                } else {
                    break
                }
            }
        }
        return sum
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
