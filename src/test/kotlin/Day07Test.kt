import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day07Test: DayTest {
    override val input: String
        get() = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
        """.trimIndent()

    override val day: Day
        get() = Day07()

    @Test
    override fun testPart1() {
        assertEquals(3749, day.solvePart1(input))
    }

    @Test
    override fun testPart2() {
        assertEquals(11387, day.solvePart2(input))
    }
}