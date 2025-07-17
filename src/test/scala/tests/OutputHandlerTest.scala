package tests
import morsecode.{InputHandler, OutputHandler}
import org.scalatest.funsuite.AnyFunSuite

import java.io.{ByteArrayOutputStream, PrintStream}

class OutputHandlerTest extends AnyFunSuite {
  test("OutputHandler gives translation back to user") {
    val outputHandler = new OutputHandler()

    val outCapture = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(outCapture)) {

      outputHandler.displayOutput("Something!!!")
    }
    val output = outCapture.toString.trim
    assert(output.contains("Your translation is:"))
    assert(output.contains("Something!!!"))
  }

}
