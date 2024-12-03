class Day02 : Day {

    override val fileName: String
        get() = "Day02"

    override fun solvePart1(input: String): Int {
        val inputLines = input.asLines()
        return inputLines.count { line ->
            val numbers = convertLineToNumbers(line)
            checkNumbers(numbers)
        }
    }

    override fun solvePart2(input: String): Int {
        val inputLines = input.asLines()
        return inputLines.count { line ->
            val numbers = convertLineToNumbers(line)
            checkNumbers(numbers) || numbers.indices.any { filterIndex ->
                checkNumbers(numbers.filterIndexed { index, i -> index != filterIndex })
            }
        }
    }

    private fun convertLineToNumbers(line: String): List<Int> {
        return line.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
    }

    private fun checkNumbers(numbers: List<Int>): Boolean {
        val numbersWindowed = numbers.windowed(2).map { it.first() - it.last() }
        return numbersWindowed.all { it in 1..3 } || numbersWindowed.all { it in -3..-1 }
    }
}