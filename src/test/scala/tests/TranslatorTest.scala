package tests
import morsecode.{InputHandler, MorseCode, Translator}
import org.scalatest.funsuite.AnyFunSuite

class TranslatorTest extends AnyFunSuite {
  test("inverseMorse flips the morse code keys and values") {
    val morseCode = MorseCode.morseCode
    val inputHandler = new InputHandler()
    val translator = new Translator(inputHandler)
    val inverseMorse = translator.inverseMorse
    assert(inverseMorse.size == morseCode.size)
    morseCode.foreach { case (char, morse) =>
      assert(inverseMorse.contains(morse))
      assert(inverseMorse(morse) == char)
    }}
    test("Translator translates from morse to English") {
      val inputHandler = new InputHandler()
      inputHandler.initialOption("2")
      inputHandler.translatable=".... . .-.. .-.. ---"
      val translator = new Translator(inputHandler)
      val result = translator.translate()
      assert(result == "HELLO")
    }
    test("Translator translates from English to morse") {
      val inputHandler = new InputHandler()
      inputHandler.initialOption("1")
      inputHandler.translatable="hello"
      val translator = new Translator(inputHandler)
      val result = translator.translate()
      assert(result == ".... . .-.. .-.. ---")
    }
test("When translating from English to morse, Translator ignores numbers"){
  val inputHandler = new InputHandler()
  inputHandler.initialOption("1")
  inputHandler.translatable="hello 2"
  val translator = new Translator(inputHandler)
  val result = translator.translate()
  assert(result == ".... . .-.. .-.. --- / 2")
}
  test("When translating from English to morse, Translator wraps special characters"){
    val inputHandler = new InputHandler()
    inputHandler.initialOption("1")
    inputHandler.translatable="hello 2?"
    val translator = new Translator(inputHandler)
    val result = translator.translate()
    assert(result == ".... . .-.. .-.. --- / 2 [?]")
  }
  test("When translating from morse to English, Translator will throw an unknown error for unknown morse letters and ask to try again"){
    val inputHandler = new InputHandler()
    inputHandler.initialOption("2")
    inputHandler.translatable = ".....--"
    val translator = new Translator(inputHandler)
    val result = translator.translate()
    assert(result == "Sorry, that's not valid morse!")
  }
}
