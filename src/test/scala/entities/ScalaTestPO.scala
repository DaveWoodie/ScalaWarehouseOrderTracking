package entities

import org.scalatest._
  
class ScalaTestPO extends FlatSpec with Matchers {
  
  
  "A Purchase Order" should "be initialised with the correct values inputted into the construtor" in {
    
    val pO = new purchaseOrder("1", "2", "3", "4", "5", "6")
   
    pO.purchaseID.value should include ("1")
    pO.datePlaced.value should include ("2")
    pO.dateRecieved.value should include ("3")
    pO.statusID.value should include ("4")
    pO.employeeID.value should include ("5")
    pO.supplierID.value should include ("6")
  }
}