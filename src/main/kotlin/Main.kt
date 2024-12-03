fun main() {
    val day: Day = Day02()
    val input = readInput(day.fileName)
    day.solvePart1(input).println()
    day.solvePart2(input).println()
}