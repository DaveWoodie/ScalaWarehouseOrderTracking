/**
 * @author dwoodward
 */

package controllers

import connectors.SQLConnector
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import entities.purchaseOrderLine

class purchaseOrderLineController {
  
  /**
   * Method to add a new purchase order line.
   */
  def addNewLine(data: purchaseOrderLine, supplierID: String): Unit = {
    val con: SQLConnector = new SQLConnector

    val s: String = "INSERT INTO mydb.purchaseorderline (idpurchaseorder, idsupplier, iditem, quantity, damagedquantity) VALUES ('" + data.purchaseOrderID.value.toString() + "', '"  + supplierID + "', '" + data.itemID.value.toString() +"', '" + data.quantity.value.toString() + "', 'unchecked')";

    try {
      con.doUpdate(s)
    }
    catch {
      case ex: SQLException => {
        println("SQLException")
        println(ex.getStackTrace)
      } 
    }
  }
  
  /**
   * Method to get the pruchase order Lines of an order with product order ID, ID.
   */
  def getPurchaseOrderLines(ID : String): ObservableBuffer[purchaseOrderLine] = {
    
    val con: SQLConnector = new SQLConnector
    var fields: Array[String] = new Array[String](4)
    fields(0) = "iditem"
    fields(1) = "idpurchaseorder"
    fields(2) = "quantity"
    fields(3) = "damagedquantity"

    val s: String = "SELECT iditem, idpurchaseorder, quantity, damagedquantity FROM mydb.purchaseorderline WHERE idpurchaseorder = " + ID + ""

    var results: ObservableBuffer[purchaseOrderLine] = new ObservableBuffer[purchaseOrderLine]
    
    try {
      results = con.doPurchaseOrderLineQuery(s, fields, results)
 //     results.get(0).printPurchaseOrder()
    }
    catch {
      case ex: SQLException => {
        println("SQLException")
        println(ex.getStackTrace)
      } 
    }
    return results
    
  }
  
  /**
   * Method to update the levels of damaged stock in an order
   */
  def updateDamagedStock(orderID: String, itemID : String, quantity : String): Unit = {
    val con: SQLConnector = new SQLConnector

    val s: String = "UPDATE mydb.purchaseorderline SET damagedquantity=" + quantity + " WHERE (iditem = " + itemID + " AND idpurchaseorder = " + orderID + ")"

    try {
      con.doUpdate(s)
    }
    catch {
      case ex: SQLException => {
        println("SQLException")
        println(ex.getStackTrace)
      } 
    }
  }
  
}