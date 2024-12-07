class Day06 : Day() {

    private val obstacleChar = '#'
    private val freeWayChar = '.'
    private val guardChar = '^'

    override fun solvePart1(input: String): Long {
        val matrix = parseDataAsMatrix(input)
        val (guardY, column) = findGuardPosition(matrix)
        val initialOrientation = Orientation.UP

        val visitedPositions = makePath(
            sizeX = matrix.first().size,
            sizeY = matrix.size,
            matrix = matrix,
            orientation = initialOrientation,
            currX = column,
            currY = guardY,
            visitedPositions = mutableListOf(VisitedPosition(x = column, y = guardY, orientation = initialOrientation)),
        ).visitedPositions.distinctBy { Pair(it.x, it.y) }

        return visitedPositions.size.toLong()
    }

    override fun solvePart2(input: String): Long {
        val matrix = parseDataAsMatrix(input)
        val (guardY, guardX) = findGuardPosition(matrix)
        val initialOrientation = Orientation.UP

        // сперва узнаем, по какому пути прошел изначально - на эти точки и будем ставить препятствия
        val initialPath = makePath(
            sizeX = matrix.first().size,
            sizeY = matrix.size,
            matrix = matrix,
            orientation = initialOrientation,
            currX = guardX,
            currY = guardY,
            visitedPositions = mutableListOf(VisitedPosition(x = guardX, y = guardY, orientation = initialOrientation)),
        ).visitedPositions.distinctBy { Pair(it.x, it.y) }

        var counter = 0
        val initialPathFiltered = initialPath.toMutableList().also {
            it.removeIf { it.x == guardX && it.y == guardY }
        }
        initialPathFiltered.forEach { visitedPosition ->
            matrix[visitedPosition.y][visitedPosition.x] = obstacleChar
            val visitedPositions = makePath(
                sizeX = matrix.first().size,
                sizeY = matrix.size,
                matrix = matrix,
                orientation = initialOrientation,
                currX = guardX,
                currY = guardY,
                visitedPositions = mutableListOf(
                    VisitedPosition(
                        x = guardX,
                        y = guardY,
                        orientation = initialOrientation
                    )
                ),
            )
            if (visitedPositions.looped) {
                counter++
            }
            matrix[visitedPosition.y][visitedPosition.x] = freeWayChar
        }
        return counter.toLong()
    }

    private fun findGuardPosition(matrix: Array<CharArray>): Pair<Int, Int> {
        for (i in matrix.indices) {
            val indexOfChar = matrix[i].indexOf(guardChar)
            if (indexOfChar >= 0) {
                return Pair(i, indexOfChar)
            }
        }
        throw IllegalStateException("guard position is not found")
    }

    private tailrec fun makePath(
        sizeX: Int,
        sizeY: Int,
        matrix: Array<CharArray>,
        orientation: Orientation,
        currX: Int,
        currY: Int,
        visitedPositions: MutableList<VisitedPosition>,
    ): VisitedPositionsData {
        val newX = currX + orientation.moveX
        val newY = currY + orientation.moveY
        // вышел за пределы матрицы - возвращаем посещенные координаты без добавления новой координаты в список посещенных
        if (newX !in 0..<sizeX || newY !in 0..<sizeY) {
            return VisitedPositionsData(visitedPositions, false)
        } else if (visitedPositions.contains(VisitedPosition(newX, newY, orientation))) { // посетил ту же точку, которую посещал ранее в том же направлении - вошел в цикл
            return VisitedPositionsData(visitedPositions, true)
        } else {
            val char = matrix[newY][newX]
            // наткнулся на препятствие - повернулся без добавления координаты в список посещенных
            if (char == obstacleChar) {
                val newOrientation = when (orientation) {
                    Orientation.UP -> Orientation.RIGHT
                    Orientation.DOWN -> Orientation.LEFT
                    Orientation.LEFT -> Orientation.UP
                    Orientation.RIGHT -> Orientation.DOWN
                }
                return makePath(sizeX, sizeY, matrix, newOrientation, currX, currY, visitedPositions)
            } else {
                // пошел дальше, добавив координату в список посещенных
                visitedPositions.add(VisitedPosition(newX, newY, orientation))
                return makePath(sizeX, sizeY, matrix, orientation, newX, newY, visitedPositions)
            }
        }
    }

    private enum class Orientation(val moveX: Int, val moveY: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0)
    }

    private data class VisitedPosition(
        val x: Int,
        val y: Int,
        val orientation: Orientation,
    )

    private data class VisitedPositionsData(
        val visitedPositions: List<VisitedPosition>,
        val looped: Boolean,
    )

    private fun parseDataAsMatrix(input: String): Array<CharArray> {
        val lines = input.asLines()
        val array = Array(lines.size) { CharArray(lines.first().length) }
        for (y in lines.indices) {
            val line = lines[y]
            for (x in line.indices) {
                array[y][x] = line[x]
            }
        }
        return array
    }
}