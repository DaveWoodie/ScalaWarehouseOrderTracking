package controllers

import org.scalatest._

import com.qa.controllers.NewPurchaseOrderController;

class ScalaTestNPOC extends FlatSpec with Matchers {
  
  "The makePO " should "return the following values that have been inputted into the constructor" in {
    val poc = new NewPurchaseOrderController
    assertResult("1")(poc.makePO("1", "3").purchaseID.value)
    assertResult("TBC")(poc.makePO("1", "3").dateRecieved.value)
    assertResult("1")(poc.makePO("1", "3").statusID.value)
    assertResult("1")(poc.makePO("1", "3").employeeID.value)
    assertResult("3")(poc.makePO("1", "3").supplierID.value)
  }
}