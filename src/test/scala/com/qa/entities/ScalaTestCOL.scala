package com.qa.entities

import org.scalatest._

class ScalaTestCOL extends FlatSpec with Matchers {
  
   "A Customer Line Order" should "be initialised with the correct values inputted into the construtor and 'Not Implemented' location" in {
    
    val pOL = new customerOrderLine("1", "2")
   
    pOL.itemID.value should include ("1")
    pOL.quantity.value should include ("2")
    pOL.location.value should include ("Not Implemented")
  }
  
}