package controllers

import connectors.SQLConnector
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import Entities.purchaseOrderLine

class purchaseOrderLineController {
  
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
  
  def updateDamagedStock(orderID: String, itemID : String, quantity : String): Unit = {
    val con: SQLConnector = new SQLConnector

    val s: String = "UPDATE mydb.purchaseorderline SET damagedquantity=" + quantity + " WHERE (iditem = " + itemID + " AND idpurchaseorder = " + orderID + ")"

    try {
      con.doPurchaseOrderLineUpdate(s)
    }
    catch {
      case ex: SQLException => {
        println("SQLException")
        println(ex.getStackTrace)
      } 
    }
  }
  
}