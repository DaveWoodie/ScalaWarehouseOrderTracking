package trials

import connectors.SQLConnector
import java.sql.SQLException
import scalafx.collections.ObservableBuffer
import Entities.purchaseOrder

object POTrialConnection {
  
  def main(args: Array[String]): Unit = {
    
    val con: SQLConnector = new SQLConnector
    var fields: Array[String] = new Array[String](3)
    fields(0) = "dateplaced"
    fields(1) = "idpurchaseorderstatus"
    fields(2) = "idSupplier"
    val s: String = "SELECT dateplaced, idpurchaseorderstatus, idSupplier FROM mydb.purchaseorder WHERE idpurchaseorder = 1"
    
    var results: ObservableBuffer[purchaseOrder] = new ObservableBuffer[purchaseOrder]
    
    try {
      
      results = con.doPurchaseOrderQuery(s, fields, results)
      results.get(0).printPurchaseOrder()
    }
    catch {
      case ex: SQLException => {
        println("SQLException")
        println(ex.getStackTrace)
      } 
    }
  
  }
}