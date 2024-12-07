class Day03: Day {
    override val fileName: String
        get() = "Day03"

    override fun solvePart1(input: String): Long {
        val pattern = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
        return pattern.findAll(input).sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }.toLong()
    }

    override fun solvePart2(input: String): Long {
        val pattern = Regex("(mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\))")
        val matches = pattern.findAll(input).map { it.groupValues }

        var isEnabled = true
        var result = 0L
        for (match in matches) {
           when (match[0]) {
               "do()" -> {
                   isEnabled = true
               }
               "don't()" -> {
                   isEnabled = false
               }
               else -> {
                   if (isEnabled) {
                       result += match[2].toLong() * match[3].toLong()
                   }
               }
           }
        }

        return result
    }
}