package entities

import org.scalatest._

import com.qa.entities.customerOrder;

class ScalaTestCO extends FlatSpec with Matchers {
  
  "A Customer Order" should "be initialised with the correct values inputted into the construtor" in {
    
    val pO = new customerOrder("1", "2")
   
    pO.customerID.value should include ("1")
    pO.datePlaced.value should include ("2")
    pO.dateShipped.value should include ("TBS")
    pO.isPaid.value should be (0)
    pO.idAddress.value should include ("1")
    pO.idCustomerOrderStatus.value should include ("1")
    pO.idEmployee.value should be (null)
    pO.idCustomer.value should include ("2")
  }
}