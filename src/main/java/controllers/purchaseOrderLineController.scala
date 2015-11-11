package controllers

import connectors.SQLConnector
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import Entities.purchaseOrderLine

class purchaseOrderLineController {
  
  def getPurchaseOrderLines(ID : String): ObservableBuffer[purchaseOrderLine] = {
    
    val con: SQLConnector = new SQLConnector
    var fields: Array[String] = new Array[String](3)
    fields(0) = "iditem"
    fields(1) = "idpurchaseorder"
    fields(2) = "quantity"

    val s: String = "SELECT iditem, idpurchaseorder, quantity FROM mydb.purchaseorderline WHERE idpurchaseorder = " + ID + ""

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
  
}