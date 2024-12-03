fun main() {
    val day: Day = Day03()
    val input = readInputAsString(day.fileName)
    day.solvePart1(input).println()
    day.solvePart2(input).println()
}