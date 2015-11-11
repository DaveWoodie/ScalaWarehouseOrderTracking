package controllers

import connectors.SQLConnector
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import Entities.purchaseOrder
import java.util.Calendar


class purchaseOrderController {
  
  def updatePOStatus(currentStatus : String, poid : String): Unit = {
    
    val con: SQLConnector = new SQLConnector
    val statusToUpdateTo: String = updatedStatus(currentStatus)
    val currentDate: String = Calendar.getInstance().getTime().toString()
    
    println(currentDate)
    
    val s: String = "UPDATE mydb.purchaseorder SET datedelivered='" + currentDate + "', idpurchaseorderstatus=" + statusToUpdateTo + " WHERE idpurchaseorder = " + poid + ""

    var results: ObservableBuffer[purchaseOrder] = new ObservableBuffer[purchaseOrder]
    
    try {
      con.doPurchaseOrderStatusUpdate(s)
    }
    catch {
      case ex: SQLException => {
        println("SQLException")
        println(ex.getStackTrace)
      } 
    }
  }
  
  def updatedStatus(status : String): String = status match {
    
    case "1" => "2"
    case "2" => "3"
    case _ => {
      println("Status: " + status)
      println("Incorrect Input, not sure how you've managed it!")
      "H"
    }
  }
  
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