package Entities

import scalafx.beans.property.{ObjectProperty, StringProperty, IntegerProperty}
import scalafx.scene.paint.Color

class purchaseOrderLine (itemID_ : Integer, purchaseOrderID_ : Integer, quantity_ : Integer) {
  
  val itemID = new IntegerProperty(this, "itemID", itemID_)
  val purchaseOrderID = new IntegerProperty(this, "poID", purchaseOrderID_)  
  val quantity = new IntegerProperty(this, "quantity", quantity_)  
}