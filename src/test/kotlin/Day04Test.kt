import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test: DayTest {

    override val input: String
        get() = "MMMSXXMASM\n" +
                "MSAMXMSMSA\n" +
                "AMXSXMAAMM\n" +
                "MSAMASMSMX\n" +
                "XMASAMXAMM\n" +
                "XXAMMXXAMA\n" +
                "SMSMSASXSS\n" +
                "SAXAMASAAA\n" +
                "MAMMMXMMMM\n" +
                "MXMXAXMASX"

    override val day: Day
        get() = Day04()

    @Test
    override fun testPart1() {
        assertEquals(18, day.solvePart1(input))
    }

    @Test
    override fun testPart2() {
        assertEquals(9, day.solvePart2(input))
    }
}