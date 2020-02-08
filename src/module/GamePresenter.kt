package module



class GamePresenter(private val view: GameInterface) {

    private lateinit var pseudoA: String
    private lateinit var pseudoB: String

    fun initGame() {
        view.displayWelcomeMessage()
        view.askPlayerPseudo()
    }

    fun savePlayerPseudoA(pseudo: String) {
        this.pseudoA = pseudo
        //view.askPlayerPseudo()
    }

    fun savePlayerPseudoB(pseudo: String) {
        this.pseudoB = pseudo
        askForStartingQuest()
    }

    private fun askForStartingQuest() {
        view.displayStartQuestMessage()
    }

    fun tryToLaunchPuissance4(comeToStartQuest: String?) {
        when (comeToStartQuest) {
            pseudoA -> {
                view.questStarting()
                view.questChoice()
            }
            pseudoB -> {
                view.questStarting()
                questStarting()
            }
            else -> view.questWtfAnswer()
        }
    }

    fun questStarting() {
         print("algo pour inclure les pions")
    }

    fun getPseudo(shape: Char) : String {
        return if (shape == 'X') this.pseudoA else this.pseudoB
    }
    fun isWinner(grid: Array<Array<String>>, player: String, y: Int, x: Int) : Boolean {
        var sum = 1
        val directions = getAllDirection()
        for ((i, direction) in directions.withIndex()) {
            if (i % 2 == 0) sum = 1
            var vertical = y
            var horizontal = x
            var branch = getCellByDirection(grid, vertical, horizontal, direction)
            while (branch != "" && branch == player) {
                sum += 1
                if (sum == 4) return true
                if (isTop(direction)) vertical -= 1
                if (isBot(direction)) vertical += 1
                if (isLeft(direction)) horizontal -= 1
                if (isRight(direction)) horizontal += 1
                branch = getCellByDirection(grid, vertical, horizontal, direction)
            }
        }
        return false
    }
    private fun isCell(grid: Array<Array<String>>, y: Int, x: Int) : Boolean {

        return y >= 0 && y < grid.size
                && x >= 0 && x < grid.first().size
    }
    private fun getCell(grid: Array<Array<String>>, y: Int, x: Int) : String {
        return if (isCell(grid, y, x)) grid[y][x] else ""
    }
    private fun getCellByDirection(grid: Array<Array<String>>, y: Int, x: Int, dir: String) : String {
        return when (dir) {
            "t" -> getCell(grid, y - 1, x + 0)
            "b" -> getCell(grid, y + 1, x + 0)
            "l" -> getCell(grid, y + 0, x - 1)
            "r" -> getCell(grid, y + 0, x + 1)
            "tl" -> getCell(grid, y - 1, x - 1)
            "tr" -> getCell(grid, y - 1, x + 1)
            "bl" -> getCell(grid, y + 1, x - 1)
            "br" -> getCell(grid, y + 1, x + 1)
            else -> throw Exception("Unknown Direction.")
        }
    }
    private fun isTop(dir: String) : Boolean {
        return dir == "t" || dir == "tl" || dir == "tr"
    }
    private fun isBot(dir: String) : Boolean {
        return dir == "b" || dir == "bl" || dir == "br"
    }
    private fun isLeft(dir: String) : Boolean {
        return dir == "l" || dir == "tl" || dir == "bl"
    }
    private fun isRight(dir: String) : Boolean {
        return dir == "r" || dir == "tr" || dir == "br"
    }
    private fun getAllDirection() : List<String> {
        return listOf("l", "r", "tl", "br", "t", "b", "tr", "bl")
    }
}

private const val Blanc = "o"
private const val Noir = "x"

