package morsecode

class InputHandler  {
  var option: String = ""
  var translatable: String = ""
  def initialOption(languageOption: String): Unit = {


    languageOption match {
      case "1" => option = "Morse"
      case "2" => option = "English"
    }
    println(s"You've chosen $option")
  }
  def translateOption(text: String): Unit = {
    println(s"Let's get $text translated into $option")
    translatable = text
  }



}
