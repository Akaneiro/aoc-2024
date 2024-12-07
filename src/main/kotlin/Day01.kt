import kotlin.math.absoluteValue

class Day01 : Day {

    override val fileName: String = "Day01"

    override fun solvePart1(input: String): Long {
        val inputLines = input.asLines()
        val (left, right) = makeLists(inputLines)
        return left.sorted()
            .zip(right.sorted())
            .sumOf { (it.first - it.second).absoluteValue }
    }

    override fun solvePart2(input: String): Long {
        val inputLines = input.asLines()
        val (left, right) = makeLists(inputLines)
        val elementsFrequencies = right.groupingBy { it }.eachCount()
        return left.sumOf { it * elementsFrequencies.getOrDefault(it, 0) }
    }

    private fun makeLists(input: List<String>): Pair<MutableList<Long>, MutableList<Long>> {
        val left = mutableListOf<Long>()
        val right = mutableListOf<Long>()
        input.forEach {
            left.add(it.substringBefore(" ").toLong())
            right.add(it.substringAfterLast(" ").toLong())
        }
        return left to right
    }
}