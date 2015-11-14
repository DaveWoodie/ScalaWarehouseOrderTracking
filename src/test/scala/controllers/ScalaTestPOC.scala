package controllers

import org.scalatest._

class ScalaTestPOC extends FlatSpec with Matchers {
  
  "The updatedStatus method" should "return '2' if the input '1' is entered" in {
    val poc = new purchaseOrderController
    assertResult("2")(poc.updatedStatus("1"))
  }
  
  "The updatedStatus method" should "return '3' if the input '2' is entered" in {
    val poc = new purchaseOrderController
    assertResult("3")(poc.updatedStatus("2"))
  }
  
  "The updatedStatus method" should "return 'H' if the input 'anything' is entered" in {
    val poc = new purchaseOrderController
    assertResult("H")(poc.updatedStatus("adsfdaxc"))
  }
}