class Day07 : Day {

    override val fileName: String
        get() = "Day07"

    override fun solvePart1(input: String): Long {
        val lines = input.asLines()
        var sum = 0L
        lines.forEach { line ->
            val (testValue, numbers) = convertLine(line)
            var possibles = mutableListOf(numbers.removeAt(0))
            while (numbers.isNotEmpty()) {
                val currentNumber = numbers.removeAt(0)
                val temp = mutableListOf<Long>()
                possibles.forEach {
                    temp.add(it + currentNumber)
                    temp.add(it * currentNumber)
                }
                possibles = temp
            }
            if (possibles.contains(testValue)) {
                sum += testValue
            }
        }
        return sum
    }

    override fun solvePart2(input: String): Long {
        val lines = input.asLines()
        var sum = 0L
        lines.forEach { line ->
            val (testValue, numbers) = convertLine(line)
            var possibles = mutableListOf(numbers.removeAt(0))
            while (numbers.isNotEmpty()) {
                val currentNumber = numbers.removeAt(0)
                val temp = mutableListOf<Long>()
                possibles.forEach {
                    temp.add(it + currentNumber)
                    temp.add(it * currentNumber)
                    temp.add((it.toString() + currentNumber.toString()).toLong())
                }
                possibles = temp
            }
            if (possibles.contains(testValue)) {
                sum += testValue
            }
        }
        return sum
    }

    private fun convertLine(line: String): Pair<Long, MutableList<Long>> {
        val testValue = line.substringBefore(":").toLong()
        val numbers = line.substringAfter(":").trim().split(" ").map { it.toLong() }.toMutableList()
        return testValue to numbers
    }
}