package com.qa.graphics.datacollectors

import scalafx.collections.ObservableBuffer
import com.qa.entities.purchaseOrder
import com.qa.controllers.purchaseOrderController

class purchaseOrderTable extends ObservableBuffer {
  
  def getPurchaseOrders(status: Int, filter: Boolean): ObservableBuffer[purchaseOrder] = {

    //println("HERE")
    val poc: purchaseOrderController = new purchaseOrderController()
    return poc.getPurchaseOrders(status, filter)
  }
}