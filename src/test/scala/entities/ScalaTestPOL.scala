package entities

import org.scalatest._

import com.qa.entities.purchaseOrderLine;
  
class ScalaTestPOL extends FlatSpec with Matchers {
  
  
  "A Purchase Line Order" should "be initialised with the correct values inputted into the construtor and 'unchecked' damaged stock" in {
    
    val pOL = new purchaseOrderLine("1", "2", "3")
   
    pOL.itemID.value should include ("1")
    pOL.purchaseOrderID.value should include ("2")
    pOL.quantity.value should include ("3")
    pOL.quantityDamg.value should include ("unchecked")
  }
}