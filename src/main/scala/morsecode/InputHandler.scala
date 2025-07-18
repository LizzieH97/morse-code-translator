package morsecode

class InputHandler {
  var option: String = ""
  var translatable: String = ""

  def initialOption(languageOption: String): Boolean = {
    languageOption match {
      case "1" => option = "Morse"
      case "2" => option = "English"
      case _ => option = ""
    }
    if (option == "") {
      println("Please type 1 or 2 :) they're the only two options :)")
      false
    } else {
      println(s"You've chosen $option")
      true
    }
  }

  def translateOption(text: String): Unit = {
    println(s"Let's get $text translated... into $option")
    translatable = text
  }
}

