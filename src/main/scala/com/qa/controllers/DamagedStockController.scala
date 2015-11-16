package com.qa.controllers

import scalafx.scene.control.{TableView}
import scalafx.scene.control.TextField
import com.qa.entities.purchaseOrderLine

/**
 * Class to nothing other than handle the updates of damaged stock in a purchase order to the database.
 */

class DamagedStockController {
  
  /**
   * Method to update the damaged stock based on the input.
   * @param t : Takes the TableView of purchase order lines.
   * @param damBox : Takes the text box that holds the values of the damaged stocks.
   */
  def updateDamagedStock(t: TableView[purchaseOrderLine], damBox: TextField): Unit = {

    t.getSelectionModel.selectedItemProperty.get.quantityDamg.value_=(damBox.text.value)
    val polc: purchaseOrderLineController = new purchaseOrderLineController
    polc.updateDamagedStock(t.getSelectionModel.selectedItemProperty.get.purchaseOrderID.value, t.getSelectionModel.selectedItemProperty.get.itemID.value, t.getSelectionModel.selectedItemProperty.get.quantityDamg.value)

  }
}