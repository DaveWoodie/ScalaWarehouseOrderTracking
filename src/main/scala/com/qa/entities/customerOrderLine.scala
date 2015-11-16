package com.qa.entities

import scalafx.beans.property.{ObjectProperty, StringProperty, IntegerProperty}

/**
 * Entity class for the customer order lines.
 */
class customerOrderLine (itemID_ : String, quantity_ : String){
  
  val itemID = new StringProperty(this, "itemID", itemID_)
  val quantity = new StringProperty(this, "quantity", quantity_)
  val location = new StringProperty(this, "location", "Not Implemented")
}