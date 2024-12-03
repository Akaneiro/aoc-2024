import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test : DayTest {

    override val input = listOf(
        "7 6 4 2 1",
        "1 2 7 8 9",
        "9 7 6 2 1",
        "1 3 2 4 5",
        "8 6 4 4 1",
        "1 3 6 7 9",
    )
    override val day: Day = Day02()

    @Test
    override fun testPart1() {
        val day = Day02()
        assertEquals(2, day.solvePart1(input))
    }

    @Test
    override fun testPart2() {
        val day = Day02()
        assertEquals(4, day.solvePart2(input))
    }
}