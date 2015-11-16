package com.qa.controllers

import java.util.Calendar
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import com.qa.entities.customerOrder
import com.qa.connectors.SQLConnector

/**
 * Class for loading and dealing with the customer orders and the database.
 */
class CustomerOrderController {

  
  /**
   * SQL to update the status.
   * NOT IMPLEMENTED YET.
   * @param currentStatus : Takes the current status of the Customer Order.
   * @param coid : Takes in a String of the customer order identification number
   */
  def updateCOStatus(currentStatus : String, coid : String): Unit = {
    
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
  
  /**
   * Little case statement for the progression of the status' of the customer orders.
   * NOT IMPLEMENTED YET, it would also exist within the CaseSorter class.
   * @param status : Takes the current status of the Customer Order.
   */
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
  
  /**
   * Gets a single customer order from the database.
   * @param coid : Takes in a String of the customer order identification number
   */
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
  
  /**
   * Gets the status of the customer orders.
   * @param status : Takes in an Int of the status.
   * @param filter : Takes in a boolean to determine wether in include or exclude a status.
   */
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
  
  /**
   * The gateway for deciding which SQL statement to use to request data from the database.
   * @param status : Takes in an Int of the status.
   * @param filter : Takes in a boolean to determine wether in include or exclude a status.
   */
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