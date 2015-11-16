package com.qa.graphics.datacollectors

import scalafx.collections.ObservableBuffer
import com.qa.entities.purchaseOrder
import com.qa.controllers.purchaseOrderController

/**
 * Class to make the purchase order table from the database.
 * This class exists in-case further filtering or logic had to be applied to the returning set of data.
 * Will be in a future implementation.
 */
class purchaseOrderTable extends ObservableBuffer {
  
  /**
   * Method to pull the data from the database and return it in an observable buffer.
   */
  def getPurchaseOrders(status: Int, filter: Boolean): ObservableBuffer[purchaseOrder] = {

    //println("HERE")
    val poc: purchaseOrderController = new purchaseOrderController()
    return poc.getPurchaseOrders(status, filter)
  }
}