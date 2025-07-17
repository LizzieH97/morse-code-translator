package tests
import morsecode.InputHandler
import org.scalatest.funsuite.AnyFunSuite

class InputHandlerTest extends AnyFunSuite {
test("Testing test") {
  assert(1+2 == 3)
}
  test("InputHandler shows morse when 1 inputted in the terminal"){
    val inputHandler = new InputHandler()
    inputHandler.initialOption("1")
    assert(inputHandler.option == "Morse")
  }
  test("InputHandler shows english when 2 inputted in the terminal"){
    val inputHandler = new InputHandler()
    inputHandler.initialOption("2")
    assert(inputHandler.option == "English")
  }
  test("InputHandler sets translatable correctly") {
    val inputHandler = new InputHandler()
    inputHandler.initialOption("1")
    inputHandler.translateOption("hello")
    assert(inputHandler.translatable == "hello")
  }

}
