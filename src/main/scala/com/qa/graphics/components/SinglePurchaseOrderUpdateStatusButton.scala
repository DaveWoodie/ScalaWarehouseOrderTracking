package com.qa.graphics.components

import scalafx.scene.control.Button
import com.qa.controllers.purchaseOrderController
import scalafx.Includes._

class SinglePurchaseOrderUpdateStatusButton extends Button {
  /**
   * Method to update the Placed Status of an order
   */
  def updatePlacedStatus(status: String, id: String): Unit = {
    val poc: purchaseOrderController = new purchaseOrderController()
    poc.updatePOStatus(status, id)
  }

  /**
   * Method to make the placed status button.
   */
  def placedStatusButton(status: String, poid: String): Button = {
    new Button {
      println("Made Placed Button")
      text = "Update Status"
      minWidth = 150
      onAction = {
        handle({
          updatePlacedStatus(status, poid)
          disable = true
        })
      }
    }
  }

  /**
   * Method to make the correct button for updating the status from delivered.
   */
  def deliveredStatusButton(status: String, poid: String): Button = {
    new Button {
      println("Made Delivered Button")
      text = "Update Status"
      minWidth = 150
      onAction = handle({
        updatePlacedStatus(status, poid)
        disable = true
      })
    }
  }

  /**
   * Method to make the complete status button.
   */
  def completeStatusButton(): Button = {
    new Button {
      println("Made Complete Button")
      text = "Order Complete"
      minWidth = 150
      disable = true
    }
  }
}