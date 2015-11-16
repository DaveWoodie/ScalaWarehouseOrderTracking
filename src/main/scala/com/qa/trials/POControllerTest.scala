/**
 * @author dwoodward
 */

package com.qa.trials

import com.qa.entities.purchaseOrder
import com.qa.controllers.purchaseOrderController
import scalafx.collections.ObservableBuffer

object POControllerTest {
  
  /**
   * Method to test if scala could pull something from the purchase orders
   */
  def main(args: Array[String]): Unit = {
    
    val p: purchaseOrderController = new purchaseOrderController()
    val o: ObservableBuffer[purchaseOrder] = p.getPurchaseOrders(2, false)
    
    var k = 0
    for(i <- o) {
      o.get(k).printPurchaseOrder()
      k+=1
    }
    
  }
}