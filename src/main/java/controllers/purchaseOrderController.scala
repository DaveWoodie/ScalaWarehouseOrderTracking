package controllers

import connectors.SQLConnector
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import Entities.purchaseOrder

class purchaseOrderController {
  
  def getSinglePO(poid: String): ObservableBuffer[purchaseOrder] = {
    
    val con: SQLConnector = new SQLConnector
    var fields: Array[String] = new Array[String](6)
    fields(0) = "idpurchaseorder"
    fields(1) = "dateplaced"
    fields(2) = "datedelivered"
    fields(3) = "idpurchaseorderstatus"
    fields(4) = "idEmployee"
    fields(5) = "idSupplier"
    
    val s: String = "SELECT idpurchaseorder, dateplaced, datedelivered, idpurchaseorderstatus, idEmployee,  idSupplier FROM mydb.purchaseorder WHERE idpurchaseorder = " + poid + ""

    var results: ObservableBuffer[purchaseOrder] = new ObservableBuffer[purchaseOrder]
    
    try {
      results = con.doPurchaseOrderQuery(s, fields, results)
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
  
  def getPurchaseOrders(status: Int, filter: Boolean): ObservableBuffer[purchaseOrder] = {
    
    val con: SQLConnector = new SQLConnector
    var fields: Array[String] = new Array[String](6)
    fields(0) = "idpurchaseorder"
    fields(1) = "dateplaced"
    fields(2) = "datedelivered"
    fields(3) = "idpurchaseorderstatus"
    fields(4) = "idEmployee"
    fields(5) = "idSupplier"
    val s: String = thePurchaseOrderGateway(status, filter)

    var results: ObservableBuffer[purchaseOrder] = new ObservableBuffer[purchaseOrder]
    
    try {
      results = con.doPurchaseOrderQuery(s, fields, results)
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
  
  def thePurchaseOrderGateway(status: Int, filter: Boolean): String = {
    
    if (status != 0) {
      if (filter == false) {
        "SELECT idpurchaseorder, dateplaced, datedelivered, idpurchaseorderstatus, idEmployee,  idSupplier FROM mydb.purchaseorder WHERE idpurchaseorderstatus = '" + status + "'"
      }
      else {
        "SELECT idpurchaseorder, dateplaced, datedelivered, idpurchaseorderstatus, idEmployee,  idSupplier FROM mydb.purchaseorder WHERE idpurchaseorderstatus != '" + status + "'"
      }
    }
    else {
      "SELECT idpurchaseorder, dateplaced, datedelivered, idpurchaseorderstatus, idEmployee,  idSupplier FROM mydb.purchaseorder"
    }
  }
}