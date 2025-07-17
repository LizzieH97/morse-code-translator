package morsecode

import scala.io.StdIn.readLine

object Main extends App {
  val inputHandler = new InputHandler()
  val outputHandler = new OutputHandler()
  val translator = new Translator(inputHandler)

  def run(): Unit = {
    println("\n🔤 Welcome to your Morse Code Translator!")
    println("What do you want to translate?\n 1) English to Morse Code \n 2) Morse Code to English")

    val languageOption = readLine()
    inputHandler.initialOption(languageOption)

    println("\nType what you want to translate below:")
    val translatable = readLine()
    inputHandler.translateOption(translatable)

    val result = translator.translate()
    outputHandler.displayOutput(result)

    println("\nDo you want to translate something else? \n 1) Yes please! \n 2) I'm okay, thanks anyway!")
    val again = readLine().toLowerCase
    if (again == "1") run()
    else println("\n Thanks for using the translator! Good luck with your morse code shenanigans :)")
  }

  run()

}




