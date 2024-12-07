class Day02 : Day() {

    override fun solvePart1(input: String): Long {
        val inputLines = input.asLines()
        return inputLines.count { line ->
            val numbers = convertLineToNumbers(line)
            checkNumbers(numbers)
        }.toLong()
    }

    override fun solvePart2(input: String): Long {
        val inputLines = input.asLines()
        return inputLines.count { line ->
            val numbers = convertLineToNumbers(line)
            checkNumbers(numbers) || numbers.indices.any { filterIndex ->
                checkNumbers(numbers.filterIndexed { index, i -> index != filterIndex })
            }
        }.toLong()
    }

    private fun convertLineToNumbers(line: String): List<Long> {
        return line.split(" ").filter { it.isNotBlank() }.map { it.toLong() }
    }

    private fun checkNumbers(numbers: List<Long>): Boolean {
        val numbersWindowed = numbers.windowed(2).map { it.first() - it.last() }
        return numbersWindowed.all { it in 1..3 } || numbersWindowed.all { it in -3..-1 }
    }
}