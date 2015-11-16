package com.qa.graphics.components

import scalafx.scene.control.TableView
import com.qa.entities.purchaseOrderLine
import scalafx.collections.ObservableBuffer
import com.qa.entities.purchaseOrder
import com.qa.controllers.{CustomerOrderController, CustomerOrderLineController}
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableColumn._
import com.qa.entities.{customerOrderLine, customerOrder}

/**
 * Class to make a table of customer order lines when a single customer order line is highlighted.
 */
class CustomerOrderLineTable extends TableView[customerOrderLine] {

  /**
   * Method to make the customer order line table.
   */
  def makeCOLT(CustomerOrderID: String): TableView[customerOrderLine] = {
    val t: TableView[customerOrderLine] = buildCOLT(CustomerOrderID)
    return t
  }

  /**
   * Main method to build and populate the table of the customer order lines.
   */
  def buildCOLT(CustomerOrderID: String): TableView[customerOrderLine] = {

    //Reason for taking in the purchase order ID here is so that when the method is called
    //The individual purchase order ID that is passed can be pulled from the database
    val c: CustomerOrderLineController = new CustomerOrderLineController
    val customerOrderLines: ObservableBuffer[customerOrderLine] = c.getCustomerOrderLines(CustomerOrderID)

    val coc: CustomerOrderController = new CustomerOrderController
    val customerOrder: ObservableBuffer[customerOrder] = coc.getSingleCO(CustomerOrderID)

    new TableView[customerOrderLine](customerOrderLines) {
      minWidth = 752
      minHeight = 496

      alignmentInParent_=(scalafx.geometry.Pos.Center)
      columns ++= List(

        new TableColumn[customerOrderLine, String] {
          text = "Item ID Number"
          cellValueFactory = { _.value.itemID }
          prefWidth = 200
        },
        new TableColumn[customerOrderLine, String] {
          text = "Quantity"
          cellValueFactory = { _.value.quantity }
          prefWidth = 200
        },
        new TableColumn[customerOrderLine, String] {
          text = "Location"
          cellValueFactory = { _.value.location }
          prefWidth = 200
        })
    }
  }
}