package com.qa.controllers

import java.util.Calendar
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import com.qa.entities.customerOrder
import com.qa.connectors.SQLConnector

class CustomerOrderController {

  
  /**
   * SQL to update the status.
   */
  def updatePOStatus(currentStatus : String, coid : String): Unit = {
    
    val con: SQLConnector = new SQLConnector
    val statusToUpdateTo: String = updatedStatus(currentStatus)
    val currentDate: String = Calendar.getInstance().getTime().toString()
    
    println(currentDate)
    
    val s: String = "UPDATE mydb.customerorder SET datedelivered='" + currentDate + "', idcustomerorderstatus=" + statusToUpdateTo + " WHERE idpurchaseorder = " + coid + ""

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
  
  
  def updatedStatus(status : String): String = status match {
    
    case "1" => "2"
    case "2" => "3"
    case "3" => "4"
    case "4" => "5"
    case _ => {
      println("Status: " + status)
      println("Incorrect Input, not sure how you've managed it!")
      "H"
    }
  }
  
  def getSingleCO(coid: String): ObservableBuffer[customerOrder] = {
    
    val con: SQLConnector = new SQLConnector
    var fields: Array[String] = new Array[String](6)
    fields(0) = "idCustomerOrder"
    fields(1) = "datePlaced"
    fields(2) = "dateShipped"
    fields(3) = "idAddress"
    fields(4) = "idCustomerOrderStatus"
    fields(5) = "idEmployee"
    
    val s: String = "SELECT idCustomerOrder, datePlaced, dateShipped, idAddress, idCustomerOrderStatus,  idEmployee FROM mydb.customerorder WHERE idCustomerOrder = " + coid + ""

    var results: ObservableBuffer[customerOrder] = new ObservableBuffer[customerOrder]
    
    try {
      results = con.doCustomerOrderQuery(s, fields, results)
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
  
  def getCustomerOrders(status: Int, filter: Boolean): ObservableBuffer[customerOrder] = {
    
    val con: SQLConnector = new SQLConnector
    var fields: Array[String] = new Array[String](6)
    fields(0) = "idCustomerOrder"
    fields(1) = "datePlaced"
    fields(2) = "dateShipped"
    fields(3) = "idAddress"
    fields(4) = "idCustomerOrderStatus"
    fields(5) = "idEmployee"
    val s: String = theCustomerOrderGateway(status, filter)

    var results: ObservableBuffer[customerOrder] = new ObservableBuffer[customerOrder]
    
    try {
      results = con.doCustomerOrderQuery(s, fields, results)
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
  
  def theCustomerOrderGateway(status: Int, filter: Boolean): String = {
    
    if (status != 0) {
      if (filter == false) {
        "SELECT idCustomerOrder, datePlaced, dateShipped, idAddress, idCustomerOrderStatus,  idEmployee FROM mydb.customerorder WHERE idCustomerOrderStatus = '" + status + "'"
      }
      else {
        "SELECT idCustomerOrder, datePlaced, dateShipped, idAddress, idCustomerOrderStatus,  idEmployee FROM mydb.customerorder WHERE idCustomerOrderStatus  != '" + status + "'"
      }
    }
    else {
      "SELECT idCustomerOrder, datePlaced, dateShipped, idAddress, idCustomerOrderStatus,  idEmployee FROM mydb.customerorder"
    }
  }
}