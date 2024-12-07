abstract class Day {

    val fileName: String = this::class.java.name

    open fun solvePart1(input: String): Long = 0

    open fun solvePart2(input: String): Long = 0
}