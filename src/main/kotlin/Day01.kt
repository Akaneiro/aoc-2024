import kotlin.math.absoluteValue

class Day01 : Day {

    override val fileName: String = "Day01"

    override fun solvePart1(input: List<String>): Int {
        val (left, right) = makeLists(input)
        left.sort()
        right.sort()
        return left.sorted()
            .zip(right.sorted())
            .sumOf { (it.first - it.second).absoluteValue }
    }

    override fun solvePart2(input: List<String>): Int {
        val (left, right) = makeLists(input)
        var sum = 0
        left.forEach { number ->
            sum += number * right.count { it == number }
        }
        val elementsFrequencies = right.groupingBy { it }.eachCount()
        return left.sumOf { it * elementsFrequencies.getOrDefault(it, 0) }
    }

    private fun makeLists(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        input.forEach {
            left.add(it.substringBefore(" ").toInt())
            right.add(it.substringAfter(" ").toInt())
        }
        return left to right
    }
}