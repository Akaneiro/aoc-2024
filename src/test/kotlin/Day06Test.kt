import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test : DayTest {
    override val input: String
        get() = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
        """.trimIndent()
    override val day: Day
        get() = Day06()

    @Test
    override fun testPart1() {
        assertEquals(41, day.solvePart1(input))
    }

    @Test
    override fun testPart2() {
        assertEquals(6, day.solvePart2(input))
    }
}