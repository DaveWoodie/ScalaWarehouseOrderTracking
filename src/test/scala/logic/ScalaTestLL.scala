package logic

import org.scalatest._
import logic.loginLogic

class ScalaTestLL extends FlatSpec with Matchers {

  "The Login" should "return false if the incorrect input is entered" in {
    val l: loginLogic = new loginLogic
    assertResult(false)(l.loginCheck("sadfouidf", "fjweruionfds"))
  }

  "The Login" should " return true if the correct login input is entered" in {
    val l: loginLogic = new loginLogic
    assertResult(true)(l.loginCheck("Al", "1"))
  }
}