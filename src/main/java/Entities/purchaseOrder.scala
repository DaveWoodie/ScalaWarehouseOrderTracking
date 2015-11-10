package Entities

import scalafx.beans.property.{ObjectProperty, StringProperty, IntegerProperty}
import scalafx.scene.paint.Color

class purchaseOrder(purchaseID_ : String, dateplaced_ : String, daterecieved_ : String, statusID_ : String, employeeID_ : String, supplierID_ : String)  {
  
  
  val purchaseID = new StringProperty(this, "purchaseOrderID", purchaseID_)
  val datePlaced = new StringProperty(this, "datePlaced", dateplaced_)
  val dateRecieved = new StringProperty(this, "dateRecieved", daterecieved_)
  val statusID = new StringProperty(this, "statusID", statusID_)
  val employeeID = new StringProperty(this, "employeeID", employeeID_)  
  val supplierID = new StringProperty(this, "supplierID", supplierID_) 
//  val orderLines = new ObjectProperty(this, "orderLines", orderLines_)
  
  def printPurchaseOrder() {
    println("Purchase Order ID: " + purchaseID_ + " Date Placed: " + dateplaced_ + " Date Recieved: " + daterecieved_ + " Employee: " + employeeID_ + " Status: " + statusID_ + " SupplierID: " + supplierID_)
  }
  
}