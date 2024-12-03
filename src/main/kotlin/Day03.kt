class Day03: Day {
    override val fileName: String
        get() = "Day03"

    override fun solvePart1(input: String): Int {
        val pattern = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
        return pattern.findAll(input).sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    override fun solvePart2(input: String): Int {
        val pattern = Regex("(mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\))")
        val matches = pattern.findAll(input).map { it.groupValues }

        var isEnabled = true
        var result = 0
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
                       result += match[2].toInt() * match[3].toInt()
                   }
               }
           }
        }

        return result
    }
}