import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test: DayTest {
    override val input: String
        get() = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    private val inputPart2: String = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    override val day: Day
        get() = Day03()

    @Test
    override fun testPart1() {
        assertEquals(161, day.solvePart1(input))
    }

    @Test
    override fun testPart2() {
        assertEquals(48, day.solvePart2(inputPart2))
    }
}