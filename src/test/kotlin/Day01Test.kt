import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test : DayTest {

    override val input = listOf("3 4", "4 3", "2 5", "1 3", "3 9", "3 3")
    override val day: Day = Day01()

    @Test
    override fun testPart1() {
        assertEquals(11, day.solvePart1(input))
    }

    @Test
    override fun testPart2() {
        assertEquals(31, day.solvePart2(input))
    }
}