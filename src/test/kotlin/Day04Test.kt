import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test : DayTest {

    override val input: String = """
    MMMSXXMASM
    MSAMXMSMSA
    AMXSXMAAMM
    MSAMASMSMX
    XMASAMXAMM
    XXAMMXXAMA
    SMSMSASXSS
    SAXAMASAAA
    MAMMMXMMMM
    MXMXAXMASX
    """.trimIndent()

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