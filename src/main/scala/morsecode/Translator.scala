package morsecode

class Translator(inputHandler: InputHandler) {
  val morseCode = MorseCode.morseCode
  val inverseMorse: Map[String, Char] = morseCode.map(_.swap)

  def translate(): String = {
    inputHandler.option match {
      case "Morse" =>
        inputHandler.translatable.toUpperCase.map {
          case text if morseCode.contains(text) => morseCode(text)
          case text => s"$text"
        }.mkString(" ")

      case "English" =>
        inputHandler.translatable.split(" ").map {
          case code if inverseMorse.contains(code) => inverseMorse(code)
          case _ => '?'
        }.mkString

      case _ => "Invalid option"
    }
  }

}
