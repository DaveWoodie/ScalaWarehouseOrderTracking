package graphics.datacollectors

import scalafx.collections.ObservableBuffer
import entities.purchaseOrder
import controllers.purchaseOrderController

class purchaseOrderTable extends ObservableBuffer {
  
  def getPurchaseOrders(status: Int, filter: Boolean /*, poID: Int*/ ): ObservableBuffer[purchaseOrder] = {

    //println("HERE")
    val poc: purchaseOrderController = new purchaseOrderController()
    return poc.getPurchaseOrders(status, filter)
  }
}