package Entities

import scalafx.beans.property.{ObjectProperty, StringProperty, IntegerProperty}
import scalafx.scene.paint.Color

class purchaseOrder(dateplaced_ : Integer, statusID_ : Integer, supplierID_ : Integer, orderLines_ : Array[purchaseOrderLine])  {
  
  val datePlaced = new IntegerProperty(this, "datePlaced", dateplaced_)
  val statusID = new IntegerProperty(this, "statusID", statusID_)
  val supplierID = new IntegerProperty(this, "supplierID", supplierID_) 
  val orderLines = new ObjectProperty(this, "orderLines", orderLines_)
  
}