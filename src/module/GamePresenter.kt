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

}

private const val Blanc = "o"
private const val Noir = "x"

