class Day04 : Day {
    override val fileName: String
        get() = "Day04"

    override fun solvePart1(input: String): Long {
        val lines = input.asLines()
        return lines.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'X') {
                    ALL_DIRECTIONS.count { vector ->
                        vectorFind(lines, SEARCH_STRING, x, y, vector)
                    }
                } else 0
            }
        }.sum().toLong()
    }

    override fun solvePart2(input: String): Long {
        val lines = input.asLines()
        return lines.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'A') {
                    CORNERS
                        .map { (dx, dy) -> lines.safeAt(x + dx, y + dy) }
                        .joinToString("") in setOf("MMSS", "MSSM", "SSMM", "SMMS")
                } else false
            }
        }.count { it }.toLong()
    }

    private fun List<String>.safeAt(x: Int, y: Int): Char =
        if (y in indices && x in this[y].indices) this[y][x] else ' '

    private tailrec fun vectorFind(
        input: List<String>,
        target: String,
        x: Int,
        y: Int,
        vector: Pair<Int, Int>
    ): Boolean =
        when {
            target.isEmpty() -> true
            target.first() != input.safeAt(x, y) -> false
            else -> vectorFind(input, target.substring(1), x + vector.first, y + vector.second, vector)
        }

    companion object {
        private const val SEARCH_STRING = "XMAS"
        val ALL_DIRECTIONS = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
        val CORNERS = listOf(-1 to -1, -1 to 1, 1 to 1, 1 to -1)
    }
}