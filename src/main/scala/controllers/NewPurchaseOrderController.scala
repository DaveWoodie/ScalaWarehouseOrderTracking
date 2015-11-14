package controllers

import scalafx.collections.ObservableBuffer
import entities.purchaseOrderLine
import entities.purchaseOrder
import scalafx.application.JFXApp.PrimaryStage
import java.util.Calendar

class NewPurchaseOrderController {
  def pushPOToDB(data: ObservableBuffer[purchaseOrderLine], poid: String, supplierID: String): Unit = {
    pushPO(poid, supplierID)
    pushPOLines(data, supplierID)
  }
  /**
   * Method to push the purchase order lines to the database.
   */
  def pushPOLines(data: ObservableBuffer[purchaseOrderLine], supplierID: String): Unit = {
    val polc: purchaseOrderLineController = new purchaseOrderLineController
    def loop(data: ObservableBuffer[purchaseOrderLine]): Unit = {
      if(data.isEmpty) { 
        //println("Finished adding Purchase Order Lines")
      }
      else {
        polc.addNewLine(data.head, supplierID)
        //println("Added Purchase Order Line")
        loop(data.tail)
      }
    }
    loop(data)
  }

  /**
   * Method to push the assembled purchase order to the database.
   */
  def pushPO(poid: String, supplierID: String): Unit = {
    val poc: purchaseOrderController = new purchaseOrderController
    poc.addPO(makePO(poid.toString(), supplierID.toString()))
  }

  /**
   * Makes the purchase order from the input.
   * Hard coded values of User ID as no sessions yet implemented.
   */
  def makePO(poid: String, supplierID: String): purchaseOrder = {
    val currentDate: String = Calendar.getInstance().getTime().toString()
    //Hard coded 
    new purchaseOrder(poid, currentDate, "TBC", "1", "1", supplierID)
  }
}