package module

import java.util.*
import kotlin.system.exitProcess


class GameConsole : GameInterface {

  private var presenter: GamePresenter = GamePresenter(this)
 // private var presenterbis: GamePresenter = GamePresenter(this)
  var tableau  = arrayOf<Array<String>>()
  private var myGames: Int = 0

  init {
    presenter.initGame()
  }

  override fun displayWelcomeMessage() {
    println("Bienvenue, j'espère que tu es prêt à jouer à Puissance 4!")
  }

  override fun askPlayerPseudo() {
    print("Quel est ton pseudo joueur 1 ? ")
    val myPseudoA: String = readLine() ?: "toto"
    println("Bonjour $myPseudoA !")
    print("Quel est ton pseudo joueur 2 ? ")
    val myPseudoB: String = readLine() ?: "totoB"
    println("Bonjour $myPseudoB !")
    presenter.savePlayerPseudoA(myPseudoA)
    presenter.savePlayerPseudoB(myPseudoB)
  }


  override fun displayStartQuestMessage() {
    print("Combien de parties voulez-vous réaliser ? ")
    val reader = Scanner(System.`in`)
    val input:Int = reader.nextInt()
    if ( input == 0 || input > 5 || input !is Int)
        wrongAnswer()
    else
        myGames += input

    print("Tapez le nom du joueur qui va commencer : ")
    presenter.tryToLaunchPuissance4(readLine())
  }

    override fun wrongAnswer() {
        println("Veuillez rentrer un chiffre valide (entre 1 et 5) ")
        displayStartQuestMessage()
    }

  override fun questStarting() {
    println(RangTITLE)
    grille()
  }


  override  fun grille() {
    var line = 0
    for (i in 0..8) {
      var array = arrayOf<String>()
      for (j in 0..8) {
        array += " |_| "
      }
      tableau += array
    }

    tableau[8][3]="  X  "
    tableau[8][5]="  O  "

    for (array in tableau) {
     // print(line )
     // line = line +1
      for (value in array) {
        print(" $value")
      }
      println()
      println()
    }
      var j = 0
      while (true) {
          println("Veuillez rentrer une colonne (entre 0 et 8) ")

          val reader = Scanner(System.`in`)
          val input:Int = reader.nextInt()

          var i = 0;
          while (tableau[8 - i][input] != " |_| ") {
              i++;
          }

          if ((j % 2) == 0) {
              tableau[8 - i][input] = "  X  "
              if (presenter.isWinner(tableau, "  X  ", 8 - i, input)) {
                  print("${presenter.getPseudo('X')} is the Winner !!")
                  exitProcess(1)
              }
          } else {
              tableau[8 - i][input] = "  O  "
              if (presenter.isWinner(tableau, "  O  ", 8 - i, input)) {
                  print("${presenter.getPseudo('O')} is the Winner !!")
                  exitProcess(1)
              }
          }
          j++

          for (array in tableau) {
              // print(line )
              // line = line +1
              for (value in array) {
                  print(" $value")
              }
              println()
              println()
          }
          
      }

  }

  override fun questWtfAnswer() {
    println("Hum, Hum, ce n'est pas une bonne réponse")
    println("Fais un effort, ne serais-tu pas lire ?")
    presenter.tryToLaunchPuissance4(readLine())
  }

    override fun questChoice() {
        print("Quel colonne choisie tu pour placer ton pion ?")
        presenter.questStarting()
    }


  private fun printYesOrNoChoice() {
    println("  'Blanc' --> o")
    println("  'Noir' --> x")
  }


}

private const val QUIT = "q"