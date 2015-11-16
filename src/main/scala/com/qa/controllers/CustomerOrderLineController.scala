package com.qa.controllers

import com.qa.connectors.SQLConnector
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import com.qa.entities.customerOrderLine

/**
 * Class for loading the customer order lines. 
 */
class CustomerOrderLineController {
  
  /**
   * Gets the customer order lines from the database.
   * @param ID : Takes the  Sting value of the customer order to retrieve it's relevant order lines.
   */
  def getCustomerOrderLines(ID : String): ObservableBuffer[customerOrderLine] = {
    
    val con: SQLConnector = new SQLConnector
    var fields: Array[String] = new Array[String](3)
    fields(0) = "iditem"
    fields(1) = "idCustomerOrder"
    fields(2) = "quantity"

    val s: String = "SELECT iditem, idCustomerOrder, quantity FROM mydb.customerorderline WHERE idCustomerOrder = " + ID + ""

    var results: ObservableBuffer[customerOrderLine] = new ObservableBuffer[customerOrderLine]
    
    try {
      results = con.doCustomerOrderLineQuery(s, fields, results)
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