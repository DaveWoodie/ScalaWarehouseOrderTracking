package com.qa.graphics.components

import scalafx.scene.control.TableView
import com.qa.entities.purchaseOrderLine
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableColumn._

/**
 * Class to instanciate a new table to fill with new item lines for the creation of a new purchase order.
 */
class NewPurchaseOrderTable {
  
  /**
   * Makes a table to populate with a new purchase order.
   */
  def makeNPOLT(t: TableView[purchaseOrderLine], data: ObservableBuffer[purchaseOrderLine]): TableView[purchaseOrderLine] = {
    new TableView[purchaseOrderLine](data) {
      minWidth = 752
      minHeight = 496

      //      padding = Insets(10, 10, 10, 10)
      alignmentInParent_=(scalafx.geometry.Pos.Center)
      columns ++= List(

        new TableColumn[purchaseOrderLine, String] {
          text = "Item ID Number"
          cellValueFactory = { _.value.itemID }
          prefWidth = 200
        },
        new TableColumn[purchaseOrderLine, String] {
          text = "Quantity of items"
          cellValueFactory = { _.value.quantity }
          prefWidth = 200
        })
    }
  }
  
}