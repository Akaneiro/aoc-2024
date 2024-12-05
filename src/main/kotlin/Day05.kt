class Day05 : Day {
    override val fileName: String
        get() = "Day05"

    override fun solvePart1(input: String): Int {
        val lines = input.asLines()
        val rules = makeRulesFromInput(lines)
        val updates = makeUpdatesFromInput(lines)
        val correctUpdates = updates.filter {
            val rulesForUpdate = filterRulesForUpdate(rules, it)
            checkUpdateForRules(it, rulesForUpdate) == null
        }

        return correctUpdates.sumOf {
            it[it.size / 2]
        }
    }

    override fun solvePart2(input: String): Int {
        val lines = input.asLines()
        val rules = makeRulesFromInput(lines)
        val updates = makeUpdatesFromInput(lines)
        val incorrectUpdates = updates.filter {
            val rulesForUpdate = filterRulesForUpdate(rules, it)
            checkUpdateForRules(it, rulesForUpdate) != null
        }
        val correctUpdates = incorrectUpdates.map {
            val rulesForUpdate = filterRulesForUpdate(rules, it)
            makeUpdateCorrect(it, rulesForUpdate)
        }
        return correctUpdates.sumOf {
            it[it.size / 2]
        }
    }

    private fun makeRulesFromInput(lines: List<String>): List<Pair<Int, Int>> {
        return lines.takeWhile { it.isNotBlank() }.map { it.split("|") }
            .map { it.first().toInt() to it.last().toInt() }
    }

    private fun makeUpdatesFromInput(lines: List<String>): List<List<Int>> {
        return lines.takeLastWhile { it.isNotBlank() }.map { it.split(",") }.map { it.map { it.toInt() } }
    }

    /**
     * 1) Для [update] сперва фильтруем [rules]: rule должен содержать число, рассматриваемое в update
     * 2) для каждого rule проверяем, что оставшееся из двух чисел удовлетворяет условию этого rule.
     * Здесь идем "от противного": достаточно проверить, что оставшееся число НЕ находится в update с противоположной стороны
     *
     * Функция возвращает ошибочный rule либо null, если update соовтествует [rules]
     */
    private fun checkUpdateForRules(update: List<Int>, rules: List<Pair<Int, Int>>): Pair<Int, Int>? {
        update.forEachIndexed { updateNumberIndex, updateNumber ->
            val rulesForNumber = filterRulesForNumber(rules, updateNumber)
            rulesForNumber.forEach { rule ->
                when (updateNumber) {
                    rule.first -> {
                        val isOk = update.none { checkingNumber ->
                            val indexOfCheckingNumber = update.indexOf(checkingNumber)
                            checkingNumber == rule.second && indexOfCheckingNumber < updateNumberIndex
                        }
                        if (!isOk) {
                            return rule
                        }
                    }
                    rule.second -> {
                        val isOk = update.none { checkingNumber ->
                            val indexOfCheckingNumber = update.indexOf(checkingNumber)
                            checkingNumber == rule.first && indexOfCheckingNumber > updateNumberIndex
                        }
                        if (!isOk) {
                            return rule
                        }
                    }
                    else -> throw IllegalArgumentException("This rule is not for this update")
                }
            }
        }

        return null
    }

    private fun filterRulesForNumber(rules: List<Pair<Int, Int>>, number: Int): List<Pair<Int, Int>> {
        return rules.filter { it.first == number || it.second == number }
    }

    private fun filterRulesForUpdate(rules: List<Pair<Int, Int>>, update: List<Int>): List<Pair<Int, Int>> {
        return buildList {
            update.forEach { updateNumber ->
                addAll(filterRulesForNumber(rules, updateNumber))
            }
        }
    }

    private tailrec fun makeUpdateCorrect(update: List<Int>, rulesForUpdate: List<Pair<Int, Int>>): List<Int> {
        val errorRule = checkUpdateForRules(update, rulesForUpdate)
        return if (errorRule == null) {
            update
        } else {
            val updateNew = update.toMutableList().also {
                val updateNumberLeftIndex = update.indexOf(errorRule.first)
                val updateNumberRightIndex = update.indexOf(errorRule.second)
                it[updateNumberLeftIndex] = errorRule.second
                it[updateNumberRightIndex] = errorRule.first
            }
            makeUpdateCorrect(updateNew, rulesForUpdate)
        }
    }
}