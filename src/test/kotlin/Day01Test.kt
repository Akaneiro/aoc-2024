import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test : DayTest {

    override val input: String = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
        """.trimIndent()

    override val day: Day = Day01()

    @Test
    override fun testPart1() {
        assertEquals(11L, day.solvePart1(input))
    }

    @Test
    override fun testPart2() {
        assertEquals(31L, day.solvePart2(input))
    }
}