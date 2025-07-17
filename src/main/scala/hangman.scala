import scala.util.{Try, Success, Failure}
import scala.io.StdIn.readLine

object Hangman {

  // curried function, allows us to call it like this: hasWon("word")(Set('a', 'b'))
  // or create another function specific for the selected word
  // val selectedWordHasWon = hasWon(gameState.word)
  // selectedWordHasWon(gameState.guesses)
  def hasWon(word: String)(guesses: Set[Char]): Boolean = {
    word.toSet.subsetOf(guesses)
  }

  // Pure function: no side effects, returns a new string showing guessed letters and masked ones
  def renderWord(word: String, guesses: Set[Char]): String =
    word.map(c => if (guesses.contains(c)) c else '_').mkString(" ")

  // Higher-order function: takes a function `read` as argument
  // Uses Try to safely handle exceptions without throwing
  def getValidGuess(read: () => String)(alreadyGuessed: Set[Char], word: String): Char = {
    def attempt(): Try[Char] = Try {
      val input = read().trim.toLowerCase
      if (input.length != 1 || !input.head.isLetter)
        throw new InvalidInputException("Please enter a single letter.")

      val guess = input.head
      if (alreadyGuessed.contains(guess))
        throw new AlreadyGuessedException(guess)
      guess
    }

    // Recursive pure function for input validation — repeats until valid input is received
    def loop(): Char = {
      attempt() match {
        case Success(c) => c
        case Failure(e) =>
          println(s"Invalid guess: ${e.getMessage}")
          loop()
      }
    }
    loop()
  }

  def playGame(state: GameState): Unit = {
    println(s"\nWord:    ${renderWord(state.word, state.guesses)}")
    println(s"Lives:   ${state.lives}")
    println(s"Guessed: ${state.guesses.toList.sorted.mkString(", ")}")

    if (hasWon(state.word)(state.guesses)) {
      println(s"You won! The word was: ${state.word}")
    } else if (state.lives <= 0) {
      println(s"Game Over! The word was: ${state.word}")
    } else {
      val guess = getValidGuess(() => readLine("Enter your guess: "))(state.guesses, state.word)

      val isCorrect = state.word.contains(guess)
      if (!isCorrect) println(s"'$guess' is not in the word.")

      // Immutable update: create a new GameState instance instead of mutating
      val newGuesses = state.guesses + guess
      val newLives = if (isCorrect) state.lives else state.lives - 1
      val newState = state.copy(guesses = newGuesses, lives = newLives)

      playGame(newState) // / Recursive call with updated state, no side effects or mutation
    }
  }

  def main(args: Array[String]): Unit = {
    println("🔤 Welcome to Functional Hangman!\n")

    val words = List("scala", "functional", "immutable", "monad", "currying")

    val rand = new scala.util.Random
    val word = words(rand.nextInt(words.length))

    val initialState = GameState(word = word, guesses = Set.empty, lives = 6)

    // Start recursive game loop
    playGame(initialState)

  }
}

case class GameState(word: String, guesses: Set[Char], lives: Int)

class InvalidInputException(msg: String) extends Exception(msg)
class AlreadyGuessedException(c: Char) extends Exception(s"'$c' was already guessed.")
class NotInWordException(c: Char) extends Exception(s"'$c' is not in the word.")