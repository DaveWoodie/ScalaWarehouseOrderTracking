/**
 * @author dwoodward
 */

package com.qa.controllers

import com.qa.connectors.SQLConnector
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import com.qa.entities.purchaseOrderLine

class purchaseOrderLineController {
  
  /**
   * Method to add a new purchase order line.
   * @param data : Takes in a purchase order line to add to the database.
   * @param supplierID : Takes in a String of the supplied ID to refer to in the database.
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
   * Method to get the purchase order Lines of an order with product order ID, ID.
   * @param ID : Takes in a String parameter of the purchase order Id number to determine which purchase order lines to return.
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
   * @param orderID : Takes the purchase order ID number of the purchase order to update.
   * @param itemID : Takes the Item ID number of the items to update.
   * @param quantity : Takes the quantity damaged of the above variables.
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