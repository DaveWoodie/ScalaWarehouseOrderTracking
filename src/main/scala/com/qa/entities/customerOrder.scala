package com.qa.entities

import scalafx.beans.property.{ObjectProperty, StringProperty, IntegerProperty}

/**
 * Entity class for the customer order.
 */
class customerOrder (idCustomerOrder_ : String, datePlaced_ : String) {
  
  val customerID = new StringProperty(this, "customerID", idCustomerOrder_) 
  val datePlaced = new StringProperty(this, "datePlaced", datePlaced_) 
  val dateShipped = new StringProperty(this, "dateShipped", "TBS") 
  val isPaid = new IntegerProperty(this, "isPaid", 0)
  val idAddress = new StringProperty(this, "idAddress", "1") 
  val idCustomerOrderStatus = new StringProperty(this, "idCustomerOrderStatus", "1") 
  val idEmployee = new StringProperty(this, "idEmployee", null) 
  val idCustomer = new StringProperty(this, "idCustomer", "2") 
    
}