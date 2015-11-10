package trials

import Entities.purchaseOrder
import controllers.purchaseOrderController
import scalafx.collections.ObservableBuffer

object POControllerTest {
  
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