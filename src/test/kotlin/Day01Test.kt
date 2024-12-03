import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test : DayTest {

    override val input: String
        get() = "3   4\n" +
                "4   3\n" +
                "2   5\n" +
                "1   3\n" +
                "3   9\n" +
                "3   3\n"
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