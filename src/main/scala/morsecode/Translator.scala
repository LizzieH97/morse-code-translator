package morsecode

class Translator(inputHandler: InputHandler) {
  val morseCode = MorseCode.morseCode
  val inverseMorse: Map[String, Char] = morseCode.map(_.swap)

  def translate(): String = {
    inputHandler.option match {
      case "Morse" =>
        inputHandler.translatable.toUpperCase.map {
          case text if morseCode.contains(text) => morseCode(text)
          case c if c.isDigit => c.toString
          case text => s"[$text]"
        }.mkString(" ")

      case "English" =>
        val maybeLetters = inputHandler.translatable.split(" ").map { code =>
          inverseMorse.get(code)
        }

        if (maybeLetters.contains(None)) {
          "Sorry, that's not valid morse!"
        } else {
          maybeLetters.flatten.mkString
        }


      case _ => "Invalid option"
    }
  }

}
